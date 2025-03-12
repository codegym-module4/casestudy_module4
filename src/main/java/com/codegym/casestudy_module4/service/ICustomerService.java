package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.Customer;

import java.util.List;

public interface ICustomerService extends IService<Customer> {
    Customer findLastCustomer();

    List<Customer> findAllByCustomerType(int customerType);
}
