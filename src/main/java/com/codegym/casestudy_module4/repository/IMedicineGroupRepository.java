package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicineGroupRepository extends JpaRepository<MedicineGroup, Long> {
    List<MedicineGroup> findAllByNameContaining(String name);
    List<MedicineGroup> findAllByCodeContaining(String code);
}