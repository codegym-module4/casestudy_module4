package com.codegym.casestudy_module4.rule;

import com.codegym.casestudy_module4.annotation.UniqueSupplierCode;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.ISupplierService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UniqueSupplierCodeValidator implements ConstraintValidator<UniqueSupplierCode, Supplier> {

    @Autowired
    private ISupplierService supplierService;

    @Override
    public boolean isValid(Supplier supplier, ConstraintValidatorContext constraintValidatorContext) {
        addConstraintViolation(constraintValidatorContext, "code", "Mã không được trùng");
        if (supplier.getCode() == null) {
            return false;
        }

        Supplier s = supplierService.findByCode(supplier.getCode());
        if (s != null) {
            if (supplier.getId() != null) {
                return !Objects.equals(s.getId(), supplier.getId());
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
