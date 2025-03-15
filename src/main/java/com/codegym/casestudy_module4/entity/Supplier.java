package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE suppliers SET deleted_at = Now() WHERE id=?")
//@Where(clause = "deleted_at is null")
@FilterDef(name = "notDeletedSupplierFilter")
@Filter(name = "notDeletedSupplierFilter", condition = "deleted_at IS NULL")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    @NotBlank(message = "Mã không được để trống")
    @Size(min = 4, max = 50, message = "Mã phải từ 4 đến 50 ký tự")
    @Pattern(regexp =  "^NCC\\d+$", message = "Mã nhà cung cấp phải bắt đầu bằng 'NCC' và theo sau là các số.")
    private String code;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Tên nhóm thuốc không được để trống")
    @Size(min = 3, max = 100, message = "Tên phải từ 3 đến 100 ký tự")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên chỉ được chứa chữ cái và khoảng trắng")
    private String name;

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\\.(com|vn|org)$",
            message = "Chỉ chấp nhận email với đuôi .com, .vn, .org")
    private String email;

    @Column(name = "address")
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @Column(name = "phone")
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số.")
    private String phone;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime deletedAt;
}