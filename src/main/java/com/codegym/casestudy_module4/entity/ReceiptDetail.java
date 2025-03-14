package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity(name = "receipt_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    public ReceiptDetail(Receipt receipt, Medicine medicine, String unit, Integer price, Integer quantity) {
        this.receipt = receipt;
        this.medicine = medicine;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
    }

    public ReceiptDetail(Long id,Receipt receipt, Medicine medicine, String unit, Integer price, Integer quantity) {
        this.id = id;
        this.receipt = receipt;
        this.medicine = medicine;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
    }
}
