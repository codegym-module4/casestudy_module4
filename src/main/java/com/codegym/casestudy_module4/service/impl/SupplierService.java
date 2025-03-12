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
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }

    @Override
    public void save(Supplier s) {
        supplierRepository.save(s);
    }

    @Override
    public void update(long id, Supplier s) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null){
            s.setId(id);
            supplierRepository.save(s);
        }
    }

    @Override
    public void remove(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Supplier findById(long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public List<Supplier> findByName(String name) {
        return supplierRepository.findAllByNameContaining(name);
    }
}
