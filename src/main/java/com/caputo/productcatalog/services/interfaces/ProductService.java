package com.caputo.productcatalog.services.interfaces;

import com.caputo.productcatalog.dtos.ProductDTO;
import com.caputo.productcatalog.dtos.ProductNameDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    Page<ProductNameDTO> findAll(Pageable pageable);

    ProductDTO findById(Long id);

    ProductDTO findByIdImage(Long id);

    ProductDTO insert(ProductDTO productDTO);

    ProductDTO update(Long id, ProductDTO productDTO);

    ProductDTO updateName(Long id, String name);

    ProductDTO updateImage(Long id, String image);

    void delete(Long id);

    void deleteByPrice(Long id);
}
