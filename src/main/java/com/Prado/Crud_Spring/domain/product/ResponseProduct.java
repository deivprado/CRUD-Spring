package com.Prado.Crud_Spring.domain.product;

public record ResponseProduct(int id, String name, Double price) {

    public  ResponseProduct(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}
