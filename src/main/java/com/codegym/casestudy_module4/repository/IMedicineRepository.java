package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findAllByNameContaining(String name);
    Page<Medicine> findAllByNameContaining(String name, PageRequest of);
}