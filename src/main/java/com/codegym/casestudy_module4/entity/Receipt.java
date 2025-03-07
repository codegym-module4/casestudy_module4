package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity(name = "receipts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "doctor")
    private String doctor;

    @Column(name = "doctor_address")
    private String doctor_address;

    @Column(name = "diagnose")
    private String diagnose;

    @Column(name = "note")
    private String note;

    @Column(name = "receipt_type")
    private Integer receiptType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;
}
