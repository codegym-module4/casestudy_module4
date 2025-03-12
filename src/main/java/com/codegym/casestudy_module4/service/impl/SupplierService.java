package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Supplier;
<<<<<<< HEAD
import com.codegym.casestudy_module4.repository.ISupplierRepository;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.ISupplierService;
>>>>>>> 732731e2ebe6e4f8a01f855637362f4172ca8e92
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {
<<<<<<< HEAD

    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
=======

    @Override
    public List<Supplier> getAll() {
        return List.of();
>>>>>>> 732731e2ebe6e4f8a01f855637362f4172ca8e92
    }

    @Override
    public void save(Supplier s) {
<<<<<<< HEAD
        supplierRepository.save(s);
=======

>>>>>>> 732731e2ebe6e4f8a01f855637362f4172ca8e92
    }

    @Override
    public void update(long id, Supplier s) {
<<<<<<< HEAD
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null){
            s.setId(id);
            supplierRepository.save(s);
        }
=======

>>>>>>> 732731e2ebe6e4f8a01f855637362f4172ca8e92
    }

    @Override
    public void remove(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Supplier findById(long id) {
<<<<<<< HEAD
        return supplierRepository.findById(id).orElse(null);
=======
        return null;
>>>>>>> 732731e2ebe6e4f8a01f855637362f4172ca8e92
    }

    @Override
    public List<Supplier> findByName(String name) {
<<<<<<< HEAD
        return supplierRepository.findAllByNameContaining(name);
=======
        return List.of();
>>>>>>> 732731e2ebe6e4f8a01f855637362f4172ca8e92
    }
}
