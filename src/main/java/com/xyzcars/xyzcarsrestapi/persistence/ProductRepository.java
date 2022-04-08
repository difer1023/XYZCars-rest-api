package com.xyzcars.xyzcarsrestapi.persistence;

import com.xyzcars.xyzcarsrestapi.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}