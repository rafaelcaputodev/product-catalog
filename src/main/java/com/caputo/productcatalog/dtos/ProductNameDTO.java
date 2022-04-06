package com.caputo.productcatalog.dtos;

import com.caputo.productcatalog.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductNameDTO implements Serializable {
    public static final Long serialVersionUID = 1L;

    private Long id;
    private String name;

    public ProductNameDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
