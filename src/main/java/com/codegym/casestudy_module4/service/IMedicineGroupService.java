package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMedicineGroupService extends IService<MedicineGroup> {
    Page<MedicineGroup> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<MedicineGroup> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<MedicineGroup> findAll(Pageable pageable);
}
