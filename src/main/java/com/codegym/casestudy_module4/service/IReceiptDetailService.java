package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.ReceiptDetail;

import java.util.List;

public interface IReceiptDetailService extends IService<ReceiptDetail> {
    ReceiptDetail updateOrCreate(ReceiptDetail receiptDetail);

    List<ReceiptDetail> getReceiptDetailsByReceiptId(Long receiptId);
}
