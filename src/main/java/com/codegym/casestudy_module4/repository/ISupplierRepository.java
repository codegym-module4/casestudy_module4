package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    Page<Supplier> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Supplier> findAllByCodeContainingIgnoreCase(String code, Pageable pageable);

    boolean existsByCode(String code);
}