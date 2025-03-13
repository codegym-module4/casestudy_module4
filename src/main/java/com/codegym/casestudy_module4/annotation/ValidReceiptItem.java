package com.codegym.casestudy_module4.annotation;


import com.codegym.casestudy_module4.rule.ReceiptItemValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ReceiptItemValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidReceiptItem {
    String message() default "Thông tin thuốc không hợp lệ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
