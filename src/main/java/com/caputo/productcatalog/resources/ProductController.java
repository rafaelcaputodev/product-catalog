package com.caputo.productcatalog.resources;

import com.caputo.productcatalog.dtos.ProductDTO;
import com.caputo.productcatalog.dtos.ProductNameDTO;
import com.caputo.productcatalog.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ProductNameDTO>> findAll(Pageable pageable) {
        Page<ProductNameDTO> list = service.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}/image")
    public ResponseEntity<ProductDTO> findByIdImage(@PathVariable Long id) {
        ProductDTO dto = service.findByIdImage(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert (@Valid @RequestBody ProductDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update (@PathVariable Long id, @Valid @RequestBody ProductDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/name/{id}/{name}")
    public ResponseEntity<ProductDTO> updateName(@PathVariable Long id, @PathVariable String name){
        ProductDTO productDTO = service.updateName(id, name);
        return ResponseEntity.ok().body(productDTO);
    }

    @PatchMapping(value = "/image/{id}/{image}")
    public ResponseEntity<ProductDTO> updateImage (@PathVariable Long id, @PathVariable String image){
        ProductDTO productDTO  = service.updateImage(id, image);
        return ResponseEntity.ok().body(productDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/deleteByPrice/{id}")
    public ResponseEntity<Void> deleteByPrice (@PathVariable Long id){
        service.deleteByPrice(id);
        return ResponseEntity.noContent().build();
    }
}
