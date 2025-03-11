package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findById (long id);

    User findByUsername(String username);

    @Query("SELECT r.roleName FROM User u JOIN u.role r WHERE u.employee.id = :id")
    String findRoleNameById(@Param("id") Long id);

    @Query("SELECT MAX(CAST(SUBSTRING(u.code, 4) AS int)) FROM User u")
    Integer findMaxCode();

    User findByEmployeeId(Long id);
}