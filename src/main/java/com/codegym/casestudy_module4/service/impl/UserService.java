package com.codegym.casestudy_module4.service.impl;

import com.codegym.casestudy_module4.entity.Role;
import com.codegym.casestudy_module4.entity.User;
import com.codegym.casestudy_module4.repository.IUserRepository;
import com.codegym.casestudy_module4.service.IRoleService;
import com.codegym.casestudy_module4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

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

    @Override
    public String generateCode() {
        Integer maxCode = userRepository.findMaxCode();
        if (maxCode == null) {
            return "U001";
        }
        return "U" + String.format("%03d", maxCode + 1);
    }
}
