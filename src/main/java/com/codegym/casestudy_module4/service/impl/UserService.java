package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Role;
import com.codegym.casestudy_module4.entity.User;
import com.codegym.casestudy_module4.service.IRoleService;
import com.codegym.casestudy_module4.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public void save(User s) {

    }

    @Override
    public void update(long id, User s) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findByName(String name) {
        return List.of();
    }
}
