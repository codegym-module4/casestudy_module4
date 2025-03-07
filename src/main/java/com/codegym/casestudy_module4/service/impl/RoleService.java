package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Role;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Override
    public List<Role> getAll() {
        return List.of();
    }

    @Override
    public void save(Role s) {

    }

    @Override
    public void update(long id, Role s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Role findById(long id) {
        return null;
    }

    @Override
    public List<Role> findByName(String name) {
        return List.of();
    }
}
