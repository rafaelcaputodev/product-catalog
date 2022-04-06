package com.caputo.productcatalog.services.exceptions;

public class DatabaseException extends RuntimeException {
    public static final Long serialVersionUID = 1L;

    public DatabaseException(String message) {
        super(message);
    }
}
