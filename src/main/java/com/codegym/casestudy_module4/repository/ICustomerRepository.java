package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingIgnoreCase(String name);

    boolean existsByPhone(String phone);

    Customer findFirstByOrderByIdDesc();

    List<Customer> findAllByCustomerType(int customerType);

    @Query("SELECT c FROM customers c WHERE c.id = :id AND c.deletedAt IS NULL")
    Customer findNotDeletedById(@Param("id") long id);
}

