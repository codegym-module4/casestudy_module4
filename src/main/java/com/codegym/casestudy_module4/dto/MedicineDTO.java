package com.codegym.casestudy_module4.dto;

import com.codegym.casestudy_module4.annotation.ValidReceiptItem;
import com.codegym.casestudy_module4.entity.Medicine;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ValidReceiptItem
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {

    private Long id;

    private Medicine medicine;

    private String unit;

    private Integer quantity;

    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}