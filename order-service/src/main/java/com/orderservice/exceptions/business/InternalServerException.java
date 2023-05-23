package com.orderservice.exceptions.business;

import com.orderservice.exceptions.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends BaseRuntimeException {
    private static final String KEY = "internal.server.error";

    public InternalServerException(String message) {
        super(Map.of("message", message));
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}
