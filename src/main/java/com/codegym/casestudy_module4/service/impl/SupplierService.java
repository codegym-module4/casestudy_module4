package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.repository.ISupplierRepository;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        if (supplierRepository.existsByCode(s.getCode())) {
            throw new RuntimeException("Mã nhà cung cấp đã tồn tại!");
        }
        supplierRepository.save(s);
    }

    @Override
    public void update(long id, Supplier s) {
        s.setId(id);
        s.setCode(findById(id).getCode());
        s.setCreatedAt(findById(id).getCreatedAt());
        supplierRepository.save(s);
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
        return List.of();
    }

    @Override
    public Page<Supplier> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return supplierRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Supplier> findByCodeContainingIgnoreCase(String code, Pageable pageable) {
        return supplierRepository.findAllByCodeContainingIgnoreCase(code,pageable);
    }

    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

}