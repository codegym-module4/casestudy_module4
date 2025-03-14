package com.codegym.casestudy_module4.service.impl;


import com.codegym.casestudy_module4.entity.User;
import com.codegym.casestudy_module4.repository.IUserRepository;
import com.codegym.casestudy_module4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
        s.setId(id);
        userRepository.save(s);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public User findById(long id) {
        return userRepository.findAllById(id);
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

    @Override
    public Page<User> findByEmployeeFullName(String name, Pageable pageable) {
        return userRepository.findAllByEmployeeFullName(name, pageable);
    }

    @Override
    public Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable) {
        return userRepository.findAllByUsernameContainingIgnoreCase(username, pageable);
    }

    @Override
    public Page<User> findByCodeContainingIgnoreCase(String searchInput, Pageable pageable) {
        return userRepository.findAllByCodeContainingIgnoreCase(searchInput, pageable);
    }

    @Override
    public Page<User> findByRoleName(String searchInput, Pageable pageable) {
        return userRepository.findAllByRoleName(searchInput, pageable);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

