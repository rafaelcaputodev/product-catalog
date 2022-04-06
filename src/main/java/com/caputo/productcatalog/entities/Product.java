package com.caputo.productcatalog.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product extends Default implements Serializable {
    public static final Long serialVersionUID = 1L;

    private String name;
    private Double price;
    private String imageUri;

}
