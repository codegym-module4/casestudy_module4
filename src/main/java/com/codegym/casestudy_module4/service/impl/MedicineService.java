package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.dto.MedicineDTO;
import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.repository.IMedicineRepository;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MedicineService implements IMedicineService {

    @Autowired
    private IMedicineRepository medicineRepository;

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public void save(Medicine s) {
        medicineRepository.save(s);
    }

    @Override
    public void update(long id, Medicine s) {
        Medicine medicine = findById(id);
        ///
    }

    @Override
    public void remove(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public Medicine findById(long id) {
        return medicineRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medicine> findByName(String name) {
        return List.of();
    }

    @Override
    public Page<Medicine> findByName(String name, PageRequest of) {
        return medicineRepository.findAllByNameContaining(name, of);
    }


}
