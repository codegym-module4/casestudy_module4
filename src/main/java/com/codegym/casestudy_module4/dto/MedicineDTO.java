package com.codegym.casestudy_module4.dto;

import com.codegym.casestudy_module4.annotation.ValidReceiptItem;

@ValidReceiptItem
public class MedicineDTO {

    private Integer medicineId; // Nếu có dữ liệu, các trường khác không được rỗng

    private String unit; // Nếu có dữ liệu, các trường khác không được rỗng

    private Integer quantity; // Nếu có dữ liệu, phải là số nguyên > 0

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
