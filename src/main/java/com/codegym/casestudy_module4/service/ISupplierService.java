package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ISupplierService extends IService<Supplier> {
    Page<Supplier> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Supplier> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<Supplier> findAll(Pageable pageable);
}