package com.caputo.productcatalog.services.impl;

import com.caputo.productcatalog.dtos.ProductDTO;
import com.caputo.productcatalog.dtos.ProductNameDTO;
import com.caputo.productcatalog.entities.Product;
import com.caputo.productcatalog.services.exceptions.DatabaseException;
import com.caputo.productcatalog.services.exceptions.PriceException;
import com.caputo.productcatalog.services.exceptions.ResourceNotFoundException;
import com.caputo.productcatalog.repositories.ProductRepository;
import com.caputo.productcatalog.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductNameDTO> findAll(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(ProductNameDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProductDTO(entity);
    }

    @Override
    public ProductDTO findByIdImage(Long id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        if(entity.getImageUri().isEmpty()){
            throw new ResourceNotFoundException("Product has no image");
        }
        return new ProductDTO(entity);
    }

    @Override
    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product entity = new Product();
        copyDtoToEntity(productDTO, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Override
    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product entity = productRepository.getOne(id);
            copyDtoToEntity(productDTO, entity);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Override
    @Transactional
    public ProductDTO updateName(Long id, String name) {
        try {
            Product entity = productRepository.getOne(id);
            entity.setName(name);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Override
    @Transactional
    public ProductDTO updateImage(Long id, String image) {
        try {
            Product entity = productRepository.getOne(id);
            entity.setImageUri(image);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    @Override
    public void deleteByPrice(Long id) {
        try {
            Product product = productRepository.getOne(id);
            if(product.getPrice() == 10){
                throw new PriceException("Product with price 10 cannot be deleted!");
            }
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImageUri(dto.getImageUri());
    }
}
