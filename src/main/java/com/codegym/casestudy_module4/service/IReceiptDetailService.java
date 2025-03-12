package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.ReceiptDetail;

public interface IReceiptDetailService extends IService<ReceiptDetail> {
    ReceiptDetail updateOrCreate(ReceiptDetail receiptDetail);
}
