package com.codegym.casestudy_module4.dto;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Employee;
import jakarta.persistence.*;
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

    private Customer customer;

    private Employee employee;

    private String symptoms;

    private String doctor;

    private String doctor_address;

    private String diagnose;

    private String note;

    private LocalDate createdAt;

    private List<MedicineDTO> items;

    public OrderReceiptDTO(String code, Customer customer, Employee employee, String symptoms, String doctor, String doctor_address, String diagnose, String note, LocalDate createdAt, List<MedicineDTO> items) {
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
}
