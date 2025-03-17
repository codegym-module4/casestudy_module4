package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IReceiptRepository extends JpaRepository<Receipt, Long>, JpaSpecificationExecutor<Receipt> {
    Receipt findFirstByOrderByIdDesc();

    @Query("SELECT r FROM receipts r WHERE r.id = :id AND r.deletedAt IS NULL")
    Receipt findNotDeletedById(@Param("id") long id);
}