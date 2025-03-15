package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE customers SET deleted_at = Now() WHERE id=?")
//@Where(clause = "deleted_at is null")
@FilterDef(name = "notDeletedCustomerFilter")
@Filter(name = "notDeletedCustomerFilter", condition = "deleted_at IS NULL")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    @NotEmpty(message = "Tên không được để trống")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    @NotEmpty(message = "Điện thoại không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số.")
    private String phone;

    @Column(name = "age")
    @Min(value = 0, message = "Tuổi phải lớn hơn không")
    private Integer age;

    @Column(name = "note")
    private String note;

    @Column(name = "customer_type")
    private Integer customerType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime deletedAt;
}
