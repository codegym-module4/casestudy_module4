package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.repository.ISupplierRepository;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {
    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
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
