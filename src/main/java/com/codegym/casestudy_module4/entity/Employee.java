package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE employees SET deleted_at = Now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Pattern(regexp = "^[A-Za-z0-9À-ỹáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ ]+$", message = "Tên chỉ được chứa chữ cái, số, khoảng trắng và các ký tự tiếng Việt hợp lệ, không có ký tự đặc biệt.")
    @Size(min = 1, max = 255, message = "Tên phải từ 2 đến 50 ký tự.")
    @NotEmpty(message = "Tên không được để trống.")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "Địa chỉ không được để trống.")
    @Column(name = "address")
    private String address;

    @Pattern(regexp = "^[0-9]{9,20}$", message = "Số điện thoại phải chứa từ 9 đến 20 chữ số.")
    @NotNull(message = "Số điện thoại không được để trống.")
    @Column(name = "phone")
    private String phone;

    @Past(message = "Ngày sinh phải nhỏ hơn ngày hiện tại.")
    @Column(name = "dob")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    @Column(name = "hire_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate hireDate;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime deletedAt;
}
