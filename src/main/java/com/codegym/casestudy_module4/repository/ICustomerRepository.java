package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findFirstByOrderByIdDesc();

    List<Customer> findAllByCustomerType(int customerType);
}