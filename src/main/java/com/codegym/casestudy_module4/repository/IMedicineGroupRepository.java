package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicineGroupRepository extends JpaRepository<MedicineGroup, Long> {
}