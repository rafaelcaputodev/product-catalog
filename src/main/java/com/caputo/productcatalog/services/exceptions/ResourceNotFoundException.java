package com.caputo.productcatalog.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public static final Long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
