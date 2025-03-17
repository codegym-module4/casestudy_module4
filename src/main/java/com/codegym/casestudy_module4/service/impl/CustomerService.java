package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.repository.ICustomerRepository;
import com.codegym.casestudy_module4.service.ICustomerService;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> getAll() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedCustomerFilter");

        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        if (customer.getPhone() == null || !customer.getPhone().matches("\\d{10}")) {
            throw new IllegalArgumentException("Số điện thoại phải gồm 10 chữ số");
        }
        if (customerRepository.existsByPhone(customer.getPhone())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setStatus(1);

        customer.setCode("TEMP");

        Customer saved = customerRepository.save(customer);

        String prefix = "";
        if (saved.getCustomerType() != null) {
            if (saved.getCustomerType() == 2) {
                prefix = "KTD";
            } else if (saved.getCustomerType() == 3) {
                prefix = "KSI";
            }
        }
        saved.setCode(prefix + saved.getId());
        customerRepository.save(saved);
    }


    @Override
    public void update(long id, Customer newCustomer) {
        Customer existing = customerRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(newCustomer.getName());
            existing.setAddress(newCustomer.getAddress());
            existing.setPhone(newCustomer.getPhone());
            existing.setAge(newCustomer.getAge());
            existing.setNote(newCustomer.getNote());
            existing.setCustomerType(newCustomer.getCustomerType());
            customerRepository.save(existing);
        }
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findById(long id) {
        return customerRepository.findNotDeletedById(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedCustomerFilter");

        return customerRepository.findByNameContainingIgnoreCase(name);
    }

    public Customer findLastCustomer() {
        return customerRepository.findFirstByOrderByIdDesc();
    }

    public List<Customer> findAllByCustomerType(int customerType) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("notDeletedCustomerFilter");
        return customerRepository.findAllByCustomerType(customerType);
    }

    @Override
    public Customer updateOrCreate(Customer customer) {
        return customerRepository.save(customer);
    }
}
