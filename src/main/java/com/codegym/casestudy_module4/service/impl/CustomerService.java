package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Override
    public List<Customer> getAll() {
        return List.of();
    }

    @Override
    public void save(Customer s) {

    }

    @Override
    public void update(long id, Customer s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Customer findById(long id) {
        return null;
    }

    @Override
    public List<Customer> findByName(String name) {
        return List.of();
    }
}
