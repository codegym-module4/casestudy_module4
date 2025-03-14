package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService extends IService<Employee> {
    Page<Employee> findByFullNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Employee> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<Employee> findByRoleNameContainingIgnoreCase(String roleName, Pageable pageable);
    Page<Employee> findByAddressContainingIgnoreCase(String address, Pageable pageable);
    Page<Employee> findByPhoneContainingIgnoreCase(String phone, Pageable pageable);

    String generateCode();
    String getEmployeeRole (Long id);
}
