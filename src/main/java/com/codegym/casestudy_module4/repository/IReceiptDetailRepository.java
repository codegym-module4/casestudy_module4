package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Receipt;
import com.codegym.casestudy_module4.entity.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IReceiptDetailRepository extends JpaRepository<ReceiptDetail, Long> {
    @Modifying
    @Query("DELETE FROM receipt_detail rd WHERE rd.receipt.id = :receiptId")
    void deleteByReceiptId(@Param("receiptId") Long receiptId);
}