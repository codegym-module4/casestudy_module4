package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity(name = "medicine_groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE medicine_groups SET deleted_at = Now() WHERE id=?")
//@Where(clause = "deleted_at is null")
@FilterDef(name = "notDeletedMGFilter")
@Filter(name = "notDeletedMGFilter", condition = "deleted_at IS NULL")
public class MedicineGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    @NotBlank(message = "Mã nhóm thuốc không được để trống")
    @Size(min = 3, max = 50, message = "Mã phải từ 3 đến 50 ký tự")
    @Pattern(regexp = "^MG\\d+$", message = "Mã nhóm thuốc phải bắt đầu bằng 'MG' và theo sau là các số.")
    private String code;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Tên nhóm thuốc không được để trống")
    @Size(min = 3, max = 100, message = "Tên nhóm thuốc phải từ 3 đến 100 ký tự")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên nhóm thuốc chỉ được chứa chữ cái và khoảng trắng")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime deletedAt;
}
