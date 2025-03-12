package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.entity.Supplier;

import java.util.List;

public interface ISupplierService extends IService<Supplier> {
    List<Supplier> findByCode(String code);
}
