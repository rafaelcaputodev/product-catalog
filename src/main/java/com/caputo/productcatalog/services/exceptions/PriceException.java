package com.caputo.productcatalog.services.exceptions;

public class PriceException extends RuntimeException {
    public static final Long serialVersionUID = 1L;

    public PriceException(String message) {
        super(message);
    }
}
