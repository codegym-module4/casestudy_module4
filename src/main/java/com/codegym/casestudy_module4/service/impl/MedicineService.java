package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.repository.IMedicineRepository;
import com.codegym.casestudy_module4.service.IMedicineService;
import com.codegym.casestudy_module4.specification.MedicineSpecification;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService implements IMedicineService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IMedicineRepository medicineRepository;

    @Override
    public List<Medicine> getAll() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll();
    }

    @Override
    public void save(Medicine s) {
        medicineRepository.save(s);
    }

    @Override
    public void update(long id, Medicine medicine) {
        Medicine existMedicine = medicineRepository.findById(id).orElse(null);
        if (existMedicine != null) {
            medicine.setId((int) id);
            medicineRepository.save(medicine);
        }
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

    @Override
    public void updateByCode(String code, Medicine medicine) {

    }

    @Override
    public Medicine findByCode(String code) {
        return medicineRepository.findByCode(code);
    }

    public List<Medicine> getAllMedicine(Specification<Medicine> pageable) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(pageable);
    }

    public Page<Medicine> filterByName(Pageable pageable, String name) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.nameLike(name), pageable);
    }

    public Page<Medicine> filterByCode(Pageable pageable, String name) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.codeLike(name), pageable);
    }


    public Page<Medicine> filterByGroup(Pageable pageable, String group) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.groupLike(group), pageable);
    }

    public Page<Medicine> filterByIngredients(Pageable pageable, String ingredients) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.ingredientsLike(ingredients), pageable);
    }

    // Min
    public Page<Medicine> filterByImportPriceMin(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.importPriceMin(price), pageable);
    }

    public Page<Medicine> filterByRetailPriceMin(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.retailPriceMin(price), pageable);
    }

    public Page<Medicine> filterByWholesalePriceMin(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.wholesalePriceMin(price), pageable);
    }

    //    Max
    public Page<Medicine> filterByImportPriceMax(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.importPriceMax(price), pageable);
    }

    public Page<Medicine> filterByRetailPriceMax(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.retailPriceMax(price), pageable);
    }

    public Page<Medicine> filterByWholesalePriceMax(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.wholesalePriceMax(price), pageable);
    }

    // Equal
    public Page<Medicine> filterByImportPriceEqual(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.importPriceEqual(price), pageable);
    }

    public Page<Medicine> filterByRetailPriceEqual(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.retailPriceEqual(price), pageable);
    }

    public Page<Medicine> filterByWholesalePriceEqual(Pageable pageable, int price) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedMedicineFilter");

        return medicineRepository.findAll(MedicineSpecification.wholesalePriceEqual(price), pageable);
    }


}
