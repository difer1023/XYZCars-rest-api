package com.xyzcars.xyzcarsrestapi.persistence;

import com.xyzcars.xyzcarsrestapi.model.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}