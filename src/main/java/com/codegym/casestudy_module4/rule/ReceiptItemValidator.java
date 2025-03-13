package com.codegym.casestudy_module4.rule;

import com.codegym.casestudy_module4.annotation.ValidReceiptItem;
import com.codegym.casestudy_module4.dto.MedicineDTO;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.service.IMedicineService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceiptItemValidator implements ConstraintValidator<ValidReceiptItem, MedicineDTO> {

    @Autowired
    private IMedicineService medicineService;

    @Override
    public boolean isValid(MedicineDTO item, ConstraintValidatorContext context) {
        if (item == null) return true;
        boolean isValid = true;

        boolean hasId = item.getMedicine() != null;
        boolean hasQuantity = item.getQuantity() != null && item.getQuantity() > 0;
        boolean hasUnit = item.getUnit() != null && !item.getUnit().trim().isEmpty() && !item.getUnit().equals("");
        boolean hasPrice = item.getPrice() != null && item.getPrice() > 0;

//        Medicine medicine = hasId ? medicineService.findById(item.getMedicineId()) : null;
//        if (hasId && medicine == null) {
//            addConstraintViolation(context, "medicineId", "Thuốc không tồn tại.");
//            isValid = false;
//        }

        // Nếu có productId, các trường khác không được rỗng
        if (hasId && (!hasQuantity || !hasUnit || !hasPrice)) {
            if (!hasQuantity) addConstraintViolation(context, "quantity", "Vui lòng nhập số lượng.");
            if (!hasUnit) addConstraintViolation(context, "unit", "Vui lòng chọn đơn vị.");
            if (!hasPrice) addConstraintViolation(context, "price", "Vui lòng nhập giá.");
            isValid = false;
        }

        // Nếu có quantity, thì các trường khác không được rỗng
        if (hasQuantity && (!hasId || !hasUnit || !hasPrice)) {
            if (!hasId) addConstraintViolation(context, "medicineId", "Vui lòng chọn thuốc.");
            if (!hasUnit) addConstraintViolation(context, "unit", "Vui lòng chọn đơn vị.");
            if (!hasPrice) addConstraintViolation(context, "price", "Vui lòng nhập giá.");
            isValid = false;
        }

//        // Nếu có price, thì các trường khác không được rỗng
//        if (hasPrice && (!hasId || !hasUnit || !hasQuantity)) {
//            if (!hasId) addConstraintViolation(context, "medicineId", "Vui lòng chọn thuốc.");
//            if (!hasUnit) addConstraintViolation(context, "unit", "Vui lòng chọn đơn vị.");
//            if (!hasQuantity) addConstraintViolation(context, "quantity", "Vui lòng nhập số lượng.");
//            isValid = false;
//        }
//
//        // Nếu có unit, thì các trường khác không được rỗng
//        if (hasUnit && (!hasId || !hasQuantity || !hasPrice)) {
//            if (!hasId) addConstraintViolation(context, "medicineId", "Vui lòng chọn thuốc.");
//            if (!hasQuantity) addConstraintViolation(context, "quantity", "Vui lòng nhập số lượng.");
//            if (!hasPrice) addConstraintViolation(context, "price", "Vui lòng nhập giá.");
//            isValid = false;
//        }

        return isValid;
    }

    // Hàm helper để thêm lỗi cụ thể cho từng trường
    private void addConstraintViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
    }
}
