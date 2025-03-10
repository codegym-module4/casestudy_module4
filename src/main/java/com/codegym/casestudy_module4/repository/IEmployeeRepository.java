package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}