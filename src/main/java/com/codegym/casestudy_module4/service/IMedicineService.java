package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMedicineService extends IService<Medicine> {
    Page<Medicine> findByName(String name, PageRequest of);

}
