package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.repository.ISupplierRepository;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.ISupplierService;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierRepository supplierRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Supplier> getAll() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedSupplierFilter");

        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }

    @Override
    public void save(Supplier s) {
        supplierRepository.save(s);
    }

    @Override
    public void update(long id, Supplier s) {
        s.setId(id);
        s.setCreatedAt(findById(id).getCreatedAt());
        supplierRepository.save(s);
    }

    @Override
    public void remove(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Supplier findById(long id) {
        return supplierRepository.findNotDeletedById(id);
    }

    @Override
    public List<Supplier> findByName(String name) {
        return List.of();
    }

    @Override
    public Page<Supplier> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedSupplierFilter");
        return supplierRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Supplier> findByCodeContainingIgnoreCase(String code, Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedSupplierFilter");

        return supplierRepository.findAllByCodeContainingIgnoreCase(code,pageable);
    }

    @Override
    public Page<Supplier> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedSupplierFilter");

        return supplierRepository.findAll(pageable);
    }

}