package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.Role;
import com.codegym.casestudy_module4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}