package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IMedicineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService implements IMedicineService {

    @Override
    public List<Medicine> getAll() {
        return List.of();
    }

    @Override
    public void save(Medicine s) {

    }

    @Override
    public void update(long id, Medicine s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Medicine findById(long id) {
        return null;
    }

    @Override
    public List<Medicine> findByName(String name) {
        return List.of();
    }
}
