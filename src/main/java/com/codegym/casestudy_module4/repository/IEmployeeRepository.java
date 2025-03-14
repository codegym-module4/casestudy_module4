package com.codegym.casestudy_module4.repository;


import com.codegym.casestudy_module4.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAllByFullNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Employee> findAllByCodeContainingIgnoreCase(String code, Pageable pageable);

    @Query("SELECT e FROM employees e JOIN User u ON e.id = u.employee.id JOIN Role r ON u.role.id = r.id WHERE LOWER(r.roleName) LIKE LOWER(CONCAT('%', :roleName, '%'))")
    Page<Employee> findAllByRoleNameContainingIgnoreCase(@Param("roleName") String roleName, Pageable pageable);

    Page<Employee> findAllByAddressContainingIgnoreCase(String address, Pageable pageable);
    Page<Employee> findAllByPhoneContainingIgnoreCase(String phone, Pageable pageable);

    @Query("SELECT MAX(CAST(SUBSTRING(e.code, 4) AS int)) FROM employees e")
    Integer findMaxCode();

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.employee.id = :employeeId")
    void deleteUserByEmployeeId(@Param("employeeId") Long employeeId);

    @Modifying
    @Transactional
    @Query("DELETE FROM employees e WHERE e.id = :employeeId")
    void deleteEmployeeById(@Param("employeeId") Long employeeId);
}
