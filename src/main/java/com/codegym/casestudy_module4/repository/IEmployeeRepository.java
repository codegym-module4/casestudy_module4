package com.codegym.casestudy_module4.repository;


import com.codegym.casestudy_module4.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByFullNameContainingIgnoreCase(String name, Pageable pageable);
}
