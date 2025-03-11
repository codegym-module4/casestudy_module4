package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.entity.User;
import com.codegym.casestudy_module4.repository.IEmployeeRepository;
import com.codegym.casestudy_module4.repository.IUserRepository;
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

    @Autowired
    private IUserRepository userRepository;

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
        s.setId(id);
        employeeRepository.save(s);
    }

    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);
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
        return employeeRepository.findAllByFullNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Employee> findByCodeContainingIgnoreCase(String code, Pageable pageable) {
        return employeeRepository.findAllByCodeContainingIgnoreCase(code, pageable);
    }

    @Override
    public Page<Employee> findByRoleNameContainingIgnoreCase(String roleName, Pageable pageable) {
        return employeeRepository.findAllByRoleNameContainingIgnoreCase(roleName, pageable);
    }

    @Override
    public Page<Employee> findByAddressContainingIgnoreCase(String address, Pageable pageable) {
        return employeeRepository.findAllByAddressContainingIgnoreCase(address, pageable);
    }

    @Override
    public Page<Employee> findByPhoneContainingIgnoreCase(String phone, Pageable pageable) {
        return employeeRepository.findAllByPhoneContainingIgnoreCase(phone, pageable);
    }

    @Override
    public String generateCode() {
        Integer maxCode = employeeRepository.findMaxCode();
        if (maxCode == null) {
            return "EMP001";
        }
        return "EMP" + String.format("%03d", maxCode + 1);
    }

    @Override
    public String getEmployeeRole (Long id) {
        return userRepository.findRoleNameById(id);
    }
}