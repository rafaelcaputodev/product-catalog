package com.caputo.productcatalog.dtos;

import com.caputo.productcatalog.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    public static final Long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo Obrigatório")
    private String name;

    @Positive(message = "Preço deve ser positivo")
    private Double price;

    @NotBlank(message = "Campo Obrigatório")
    private String imageUri;

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        price = entity.getPrice();
        imageUri = entity.getImageUri();
    }
}
