package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.repository.IMedicineRepository;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService implements IMedicineService {

    @Autowired
    private IMedicineRepository medicineRepository;

    @Override
    public List<Medicine> getAll() {
        List<Medicine> listMedicines = medicineRepository.findAll();
        return listMedicines;
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
        return medicineRepository.findById(id).get();
    }

    @Override
    public List<Medicine> findByName(String name) {
        List<Medicine> listMedicines = medicineRepository.findByName(name);
        return listMedicines;
    }
}
