package com.productservice.exceptions.business;

import com.productservice.exceptions.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseRuntimeException {
    private static final String KEY = "bad.request";

    public BadRequestException(String message) {
        super(Map.of("message", message));
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}
