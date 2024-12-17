package com.purplemango.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TenantDuplicateException extends RuntimeException {
    public TenantDuplicateException() {
        super();
    }

    public TenantDuplicateException(String message) {
        super(message);
    }
}
