package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicineGroupRepository extends JpaRepository<MedicineGroup, Long> {
    Page<MedicineGroup> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<MedicineGroup> findAllByCodeContainingIgnoreCase(String code, Pageable pageable);

    boolean existsByCode(String code);
}