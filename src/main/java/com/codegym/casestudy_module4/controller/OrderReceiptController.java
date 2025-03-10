package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Receipt;
import com.codegym.casestudy_module4.service.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/receipt/order")
public class OrderReceiptController {

    @Autowired
    private IReceiptService receiptService;

    @GetMapping("/create")
    public String create(
        Model model
    ) {
        return "/receipt/order_receipt/create";
    }
}
