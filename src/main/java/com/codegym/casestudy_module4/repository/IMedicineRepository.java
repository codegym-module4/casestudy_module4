package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine, Long>, JpaSpecificationExecutor<Medicine> {

    Page<Medicine> findAllByNameContaining(String name, PageRequest of);

    Medicine findByCode(String code);

    Page<Medicine> findAll(Specification<Medicine> spec, Pageable page);

//    @Modifying
//    @Query(value = "UPDATE medicines m SET m.deletedAt = now() WHERE m.id = :idMedicine")
//    void deleteByMedicineId(@Param("id") Long idMedicine);
}