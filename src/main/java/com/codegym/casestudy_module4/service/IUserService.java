package com.codegym.casestudy_module4.service;

import com.codegym.casestudy_module4.entity.User;

public interface IUserService extends IService<User> {
    User findByUsername(String username);
}
