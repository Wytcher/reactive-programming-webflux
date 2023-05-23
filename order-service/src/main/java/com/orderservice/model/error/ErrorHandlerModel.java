package com.orderservice.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorHandlerModel {

    private LocalDateTime timestamp;
    private Integer status;
    private String path;
    private String code;
    private List<ErrorsModel> errors;
}
