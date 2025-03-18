package com.codegym.casestudy_module4.rule;

import com.codegym.casestudy_module4.annotation.UniqueMGCode;
import com.codegym.casestudy_module4.annotation.UniqueSupplierCode;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.service.IMedicineGroupService;
import com.codegym.casestudy_module4.service.ISupplierService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UniqueMGCodeValidator implements ConstraintValidator<UniqueMGCode, MedicineGroup> {

    @Autowired
    private IMedicineGroupService medicineGroupService;

    @Override
    public boolean isValid(MedicineGroup medicineGroup, ConstraintValidatorContext constraintValidatorContext) {
        addConstraintViolation(constraintValidatorContext, "code", "Mã không được trùng");
        if (medicineGroup.getCode() == null) {
            return false;
        }

        MedicineGroup md = medicineGroupService.findByCode(medicineGroup.getCode());
        if (md != null) {
            if (medicineGroup.getId() != null) {
                return Objects.equals(md.getId(), medicineGroup.getId());
            }
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
    }
}
