package com.codegym.casestudy_module4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("receipt/order")
public class OrderReceiptController {

    @GetMapping
    public String index(Model model) {
        return "receipt/order_receipt/list";
    }
}
