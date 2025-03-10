package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

public interface IReceiptService extends IService<Receipt> {
    Page<Receipt> getReceipt(Map<String, String> search, PageRequest of);

    Receipt findLastReceipt();
}
