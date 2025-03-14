package com.codegym.casestudy_module4.dto;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Employee;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReceiptDTO {

    private Long id;

    private String code;

    @NotNull(message = "Khách hàng không được để trống")
    private Customer customer;

    private Employee employee;

    @NotEmpty(message = "Triệu chứng không được để trống")
    private String symptoms;

    @NotEmpty(message = "Tên bác sĩ không được để trống")
    private String doctor;

    @NotEmpty(message = "Địa chỉ của bác sĩ không được để trống")
    private String doctor_address;

    @NotEmpty(message = "Chuẩn đoán không được để trống")
    private String diagnose;

    private String note;

    @NotNull(message = "Ngày lập không được để trống")
    @PastOrPresent(message = "Ngày lập không được phép lớn hơn thời hiện tại")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Valid
    @Size(min = 1, message = "Vui lòng chọn ít nhất 1 loại thuốc")
    private List<MedicineDTO> items;

    public OrderReceiptDTO(String code, Customer customer, Employee employee, String symptoms, String doctor, String doctor_address, String diagnose, String note, LocalDateTime createdAt, List<MedicineDTO> items) {
        this.code = code;
        this.customer = customer;
        this.employee = employee;
        this.symptoms = symptoms;
        this.doctor = doctor;
        this.doctor_address = doctor_address;
        this.diagnose = diagnose;
        this.note = note;
        this.createdAt = createdAt;
        this.items = items;
    }
    public OrderReceiptDTO(String code, Customer customer, Employee employee, String note, LocalDateTime createdAt, List<MedicineDTO> items) {
        this.code = code;
        this.customer = customer;
        this.employee = employee;
        this.note = note;
        this.createdAt = createdAt;
        this.items = items;
    }
}
