package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {


    @Override
    public List<Employee> getAll() {
        return List.of();
    }

    @Override
    public void save(Employee s) {

    }

    @Override
    public void update(long id, Employee s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Employee findById(long id) {
        return null;
    }

    @Override
    public List<Employee> findByName(String name) {
        return List.of();
    }
}
