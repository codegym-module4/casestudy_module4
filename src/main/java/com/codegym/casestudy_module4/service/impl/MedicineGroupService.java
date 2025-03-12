package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.repository.IMedicineGroupRepository;
import com.codegym.casestudy_module4.service.IMedicineGroupService;
import com.codegym.casestudy_module4.service.IMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineGroupService implements IMedicineGroupService {

    @Autowired
    private IMedicineGroupRepository medicineGroupRepository;


    @Override
    public List<MedicineGroup> getAll() {
        List<MedicineGroup> medicineGroups = medicineGroupRepository.findAll();
        return medicineGroups;
    }

    @Override
    public void save(MedicineGroup s) {
        medicineGroupRepository.save(s);
    }

    @Override
    public void update(long id, MedicineGroup s) {
        MedicineGroup medicineGroup = medicineGroupRepository.findById(id).orElse(null);
        if(medicineGroup != null) {
            s.setId(id);
            medicineGroupRepository.save(s);
        }
    }

    @Override
    public void remove(Long id) {
        medicineGroupRepository.deleteById(id);
    }

    @Override
    public MedicineGroup findById(long id) {
        return medicineGroupRepository.findById(id).orElse(null);
    }

    @Override
    public List<MedicineGroup> findByName(String name) {
        return medicineGroupRepository.findAllByNameContaining(name);
    }
}
