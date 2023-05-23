package com.orderservice.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.orderservice.dto.OrderItemRequestDTO;
import com.orderservice.dto.OrderRequestDTO;
import com.orderservice.entity.Order;
import com.orderservice.exceptions.business.BusinessRuleException;
import com.orderservice.exceptions.business.InternalServerException;
import com.orderservice.exceptions.business.ObjectNotFoundException;
import com.orderservice.model.CustomerModel;
import com.orderservice.model.ProductModel;
import com.orderservice.model.ValidateProductModel;
import com.orderservice.model.error.ErrorHandlerModel;
import com.orderservice.repository.OrderRepository;
import com.orderservice.utils.MapperUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class OrderServiceImpl implements OrderService {

    private final MapperUtils mapper = new MapperUtils();
    private final WebClient webClient;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(WebClient webClient, OrderRepository orderRepository) {
        this.webClient = webClient;
        this.orderRepository = orderRepository;
    }

    @Override
    public Mono<Page<Order>> getOrders(Pageable pageable) {
        return null;
    }

    @Override
    public Mono<Order> getOrderById(Long id) {
        return null;
    }

    @Override
    public Mono<Order> create(OrderRequestDTO orderRequestDTO) {
        Order orderEntity = mapper.map(orderRequestDTO, Order.class);
        return validateCustomer(orderRequestDTO.getIdCustomer())
                .publishOn(Schedulers.boundedElastic())
                .flatMap(customer -> this.validateProduct(orderRequestDTO.getItems())
                        .flatMap(product -> Mono
                                .fromCallable(() -> orderRepository.save(orderEntity))
                                .subscribeOn(Schedulers.boundedElastic()))
                );
    }

    @Override
    public Mono<Order> update(Long id, Order order) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private Mono<CustomerModel> validateCustomer(UUID idCustomer) {
        try {
            return webClient.get()
                    .uri("http://localhost:8080/customers/" + idCustomer)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(() -> new ObjectNotFoundException("A customer for the given id was not found")))
                    .bodyToMono(CustomerModel.class)
                    .publishOn(Schedulers.boundedElastic());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }

    }

    private Mono<ValidateProductModel> validateProduct(List<OrderItemRequestDTO> items) {
        try {
            Map<String, List<OrderItemRequestDTO>> body = new HashMap<>();
            body.put("products", items);
            return webClient.post()
                    .uri("http://localhost:8082/products/validate")
                    .bodyValue(body)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(() -> new ObjectNotFoundException("A product for the given id was not found")))
                    .onStatus(HttpStatusCode::is5xxServerError, error -> Mono.error(() -> new InternalServerException("Internal server error, please contact the administration")))
                    .bodyToMono(ValidateProductModel.class)
                    .publishOn(Schedulers.boundedElastic());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }
}
