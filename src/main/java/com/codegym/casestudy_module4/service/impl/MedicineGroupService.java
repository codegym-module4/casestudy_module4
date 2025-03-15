package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.repository.IMedicineGroupRepository;
import com.codegym.casestudy_module4.service.IMedicineGroupService;
import com.codegym.casestudy_module4.service.IMedicineService;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineGroupService implements IMedicineGroupService {

    @Autowired
    private IMedicineGroupRepository medicineGroupRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<MedicineGroup> getAll() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMGFilter");

        List<MedicineGroup> medicineGroups = medicineGroupRepository.findAll();
        return medicineGroups;
    }

    @Override
    public void save(MedicineGroup s) {
        medicineGroupRepository.save(s);
    }

    @Override
    public void update(long id, MedicineGroup medicineGroup) {
        medicineGroup.setId(id);
        medicineGroup.setCreatedAt(findById(id).getCreatedAt());
        medicineGroupRepository.save(medicineGroup);
    }

    @Override
    public void remove(Long id) {
        medicineGroupRepository.deleteById(id);
    }

    @Override
    public MedicineGroup findById(long id) {
        return medicineGroupRepository.findNotDeletedById(id);
    }

    @Override
    public List<MedicineGroup> findByName(String name) {
        return List.of();
    }


    @Override
    public Page<MedicineGroup> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMGFilter");

        return medicineGroupRepository.findAllByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<MedicineGroup> findByCodeContainingIgnoreCase(String code, Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMGFilter");

        return medicineGroupRepository.findAllByCodeContainingIgnoreCase(code, pageable);
    }

    @Override
    public Page<MedicineGroup> findAll(Pageable pageable) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMGFilter");

        return medicineGroupRepository.findAll(pageable);
    }
}
