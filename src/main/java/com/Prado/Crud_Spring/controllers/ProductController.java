package com.Prado.Crud_Spring.controllers;


import com.Prado.Crud_Spring.domain.product.Product;
import com.Prado.Crud_Spring.domain.product.ProductRepository;
import com.Prado.Crud_Spring.domain.product.RequestProduct;
import com.Prado.Crud_Spring.domain.product.ResponseProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts(){
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Validated RequestProduct data){
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Validated RequestProduct data){
        Product product = repository.getReferenceById(data.id());
        product.setName(data.name());
        product.setPrice(data.price());
        return ResponseEntity.ok(new ResponseProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        Product product = repository.getReferenceById(id);
        repository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
