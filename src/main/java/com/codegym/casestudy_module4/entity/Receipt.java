package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity(name = "receipts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE receipts SET deleted_at = Now() WHERE id=?")
@Where(clause = "deleted_at is null")
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
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Formula("(SELECT SUM(rd.price * rd.quantity) FROM receipt_detail rd WHERE rd.receipt_id = id)")
    private Integer total;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime deletedAt;

    public Receipt(String code, Customer customer, Employee employee, String symptoms, String diagnose, String doctor, String doctor_address, Integer receiptType, Integer status,LocalDateTime createdAt) {
        this.code = code;
        this.customer = customer;
        this.employee = employee;
        this.symptoms = symptoms;
        this.diagnose = diagnose;
        this.doctor = doctor;
        this.doctor_address = doctor_address;
        this.receiptType = receiptType;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Receipt(Long id, String code, Customer customer, Employee employee, String symptoms, String diagnose, String doctor, String doctor_address, Integer receiptType, Integer status,LocalDateTime createdAt) {
        this.id = id;
        this.code = code;
        this.customer = customer;
        this.employee = employee;
        this.symptoms = symptoms;
        this.diagnose = diagnose;
        this.doctor = doctor;
        this.doctor_address = doctor_address;
        this.receiptType = receiptType;
        this.status = status;
        this.createdAt = createdAt;
    }
    public Receipt( String code, Customer customer, Employee employee, String note , Integer receiptType, Integer status,LocalDateTime createdAt) {
        this.code = code;
        this.customer = customer;
        this.employee = employee;
        this.note=note;
        this.receiptType = receiptType;
        this.status = status;
        this.createdAt = createdAt;
    }

}
