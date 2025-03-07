package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Receipt;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IReceiptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService implements IReceiptService {
    @Override
    public List<Receipt> getAll() {
        return List.of();
    }

    @Override
    public void save(Receipt s) {

    }

    @Override
    public void update(long id, Receipt s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Receipt findById(long id) {
        return null;
    }

    @Override
    public List<Receipt> findByName(String name) {
        return List.of();
    }
}
