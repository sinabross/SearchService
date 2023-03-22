package com.test.searchservice.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QueryNotValidException extends RuntimeException {
    public QueryNotValidException(String message) {
        super(message);
    }
}
