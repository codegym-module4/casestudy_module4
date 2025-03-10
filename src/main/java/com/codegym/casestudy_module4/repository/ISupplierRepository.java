package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByNameContaining(String name);
}