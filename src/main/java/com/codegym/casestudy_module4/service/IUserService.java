package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface IUserService extends IService<User> {

    String generateCode();
    Page<User> findByEmployeeFullName(String name, Pageable pageable);
    Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
    Page<User> findByCodeContainingIgnoreCase(String searchInput, Pageable pageable);
    Page<User> findByRoleName(String searchInput, Pageable pageable);

    User findByUsername(String username);

}
