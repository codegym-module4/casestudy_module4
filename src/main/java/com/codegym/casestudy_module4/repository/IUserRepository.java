package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findAllById (long id);

    User findByUsername(String username);

    @Query("SELECT r.roleName FROM User u JOIN u.role r WHERE u.employee.id = :id")
    String findRoleNameById(@Param("id") Long id);

    @Query("SELECT MAX(CAST(SUBSTRING(u.code, 2) AS int)) FROM User u")
    Integer findMaxCode();

    User findByEmployeeId(Long id);

    @Query("SELECT u FROM User u JOIN u.employee e WHERE LOWER(e.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Page<User> findAllByEmployeeFullName(@Param("fullName")String name, Pageable pageable);

    Page<User> findAllByUsernameContainingIgnoreCase(String username, Pageable pageable);

    Page<User> findAllByCodeContainingIgnoreCase(String searchInput, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.role r WHERE LOWER(r.roleName) LIKE LOWER(CONCAT('%', :roleName, '%'))")
    Page<User> findAllByRoleName(@Param("roleName")String searchInput, Pageable pageable);
}