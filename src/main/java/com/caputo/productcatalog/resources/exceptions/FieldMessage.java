package com.caputo.productcatalog.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {
    public static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;
}
