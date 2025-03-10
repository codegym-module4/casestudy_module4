package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Receipt;
import com.codegym.casestudy_module4.repository.IReceiptRepository;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IReceiptService;
import com.codegym.casestudy_module4.specification.ReceiptSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReceiptService implements IReceiptService {

    @Autowired
    private IReceiptRepository receiptRepository;

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

    public Page<Receipt> getReceipt(Map<String, String> search, PageRequest pageable) {
        Specification<Receipt> spec = ReceiptSpecification.searchWithFilters(search);

        return receiptRepository.findAll(spec, pageable);
    }
}
