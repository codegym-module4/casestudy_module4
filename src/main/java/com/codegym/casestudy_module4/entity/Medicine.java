package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "medicines")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "active_ingredient")
    private String activeIngredient;

    @Column(name = "unit")
    private String unit;

    @Column(name = "conversion_unit")
    private String conversionUnit;

    @Column(name = "conversion_rate")
    private Integer conversionRate;

    @Column(name = "import_price")
    private Integer importPrice;

    @Column(name = "converted_import_price")
    private Integer convertedImportPrice;

    @Column(name = "discount_rate")
    private Integer discountRate;

    @Column(name = "vat_rate")
    private Integer vatRate;

    @Column(name = "price_with_vat")
    private Integer priceWithVat;

    @Column(name = "converted_price_with_vat")
    private Integer convertedPriceWithVat;

    @Column(name = "retail_profit_rate")
    private Integer retailProfitRate;

    @Column(name = "retail_price")
    private Integer retailPrice;

    @Column(name = "wholesale_profit_rate")
    private Integer wholesaleProfitRate;

    @Column(name = "wholesale_price")
    private Integer wholesalePrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "expiry_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiredDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "medicine_group_id")
    private MedicineGroup medicineGroup;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

}
