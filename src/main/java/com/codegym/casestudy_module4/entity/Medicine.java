package com.codegym.casestudy_module4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotNull
    @NotEmpty(message = "'Code' không được để trống")
    @Column(name = "code")
    private String code;

    @NotNull
    @NotEmpty(message = "'Tên' không được để trống")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "'Hoạt chất' không được để trống")
    @Column(name = "active_ingredient")
    private String activeIngredient;

    @NotEmpty(message = "'Đơn vị' không được để trống")
    @Column(name = "unit")
    private String unit;

    @NotEmpty(message = "'Quy đổi 'đơn vị không được để trống")
    @Column(name = "conversion_unit")
    private String conversionUnit;

    @NotEmpty(message = "'Tỷ lệ' quy đổi không được để trống")
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

    @NotEmpty(message = "'Nhập có VAT' không được để trống")
    @Column(name = "price_with_vat")
    private Integer priceWithVat;

    @NotEmpty(message = "'Nhập quy đổi có VAT' không được để trống")
    @Column(name = "converted_price_with_vat")
    private Integer convertedPriceWithVat;

    @NotEmpty(message = "'Lợi nhuận xuất chẵn' không được để trống")
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

    @NotEmpty(message = "'Ngày hết hạn' không được để trống")
    @Column(name = "expiry_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiredDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @NotEmpty(message = "'Xuất xứ' không được để trống")
    @Column(name = "madein")
    private String madein;

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
