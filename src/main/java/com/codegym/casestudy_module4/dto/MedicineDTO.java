package com.codegym.casestudy_module4.dto;

import com.codegym.casestudy_module4.annotation.ValidReceiptItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@ValidReceiptItem
public class MedicineDTO {

    private Integer medicineId;

    private String unit;

    private Integer quantity;

    private Integer price;

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
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
