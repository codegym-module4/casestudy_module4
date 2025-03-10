package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.repository.IEmployeeRepository;
import com.codegym.casestudy_module4.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee s) {
        employeeRepository.save(s);
    }

    @Override
    public void update(long id, Employee s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findByName(String name) {
        return List.of();
    }

    @Override
    public Page<Employee> findByFullNameContainingIgnoreCase(String name, Pageable pageable) {
        return employeeRepository.findByFullNameContainingIgnoreCase(name, pageable);
    }
}