package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.Customer;
import java.util.*;

import java.util.List;

public interface ICustomerService extends IService<Customer> {
    Customer findLastCustomer();

    List<Customer> findAllByCustomerType(int customerType);
    List<Customer> getAll();
    void save(Customer customer);
    void update(long id, Customer customer);
    void remove(Long id);
    Customer findById(long id);
    List<Customer> findByName(String name);

}
