package com.codegym.casestudy_module4.annotation;

import com.codegym.casestudy_module4.rule.UniqueMGCodeValidator;
import com.codegym.casestudy_module4.rule.UniqueSupplierCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueMGCodeValidator.class) // Gán Validator tương ứng
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueMGCode {
    String message() default "Mã không được trùng lặp"; // Thông báo lỗi mặc định

    Class<?>[] groups() default {}; // Cho phép nhóm validation

    Class<? extends Payload>[] payload() default {};
}
