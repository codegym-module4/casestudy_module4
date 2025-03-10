package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Receipt;
import com.codegym.casestudy_module4.entity.ReceiptDetail;
import com.codegym.casestudy_module4.service.IReceiptDetailService;
import com.codegym.casestudy_module4.service.IReceiptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptDetailService implements IReceiptDetailService {
    @Override
    public List<ReceiptDetail> getAll() {
        return List.of();
    }

    @Override
    public void save(ReceiptDetail s) {

    }

    @Override
    public void update(long id, ReceiptDetail s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public ReceiptDetail findById(long id) {
        return null;
    }

    @Override
    public List<ReceiptDetail> findByName(String name) {
        return List.of();
    }
}
