package com.codegym.casestudy_module4.ulti;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAdvice {
    private final ThymeleafHelper thymeleafHelper;

    public GlobalModelAdvice(ThymeleafHelper thymeleafHelper) {
        this.thymeleafHelper = thymeleafHelper;
    }

    @ModelAttribute("thymeleafHelper")
    public ThymeleafHelper addThymeleafHelper() {
        return thymeleafHelper;
    }
}
