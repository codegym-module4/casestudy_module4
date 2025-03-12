package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Receipt;
import com.codegym.casestudy_module4.entity.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReceiptDetailRepository extends JpaRepository<ReceiptDetail, Long> {
    List<ReceiptDetail> findByReceiptId(Long receiptId);
}