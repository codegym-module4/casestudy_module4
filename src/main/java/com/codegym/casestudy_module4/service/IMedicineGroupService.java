package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IMedicineGroupService extends IService<MedicineGroup> {
//    Page<MedicineGroup> findByName(String name, PageRequest of);
    List<MedicineGroup> findByCode(String code);
}
