package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.ISupplierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {

    @Override
    public List<Supplier> getAll() {
        return List.of();
    }

    @Override
    public void save(Supplier s) {

    }

    @Override
    public void update(long id, Supplier s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Supplier findById(long id) {
        return null;
    }

    @Override
    public List<Supplier> findByName(String name) {
        return List.of();
    }
}
