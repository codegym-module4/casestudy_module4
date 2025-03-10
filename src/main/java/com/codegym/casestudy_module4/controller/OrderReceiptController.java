package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.Receipt;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IMedicineService;
import com.codegym.casestudy_module4.service.IReceiptService;
import com.codegym.casestudy_module4.ulti.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/receipt/order")
public class OrderReceiptController {

    @Autowired
    private IReceiptService receiptService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IMedicineService medicineService;

    @GetMapping("/create")
    public String create(
        Model model
    ) {
        Receipt receipt = new Receipt();
        Receipt lastReceipt = receiptService.findLastReceipt();
        long lastReceiptId = lastReceipt == null?0:lastReceipt.getId();
        receipt.setCode("HD" + StringUtil.strPad(String.valueOf(lastReceiptId + 1), 5, '0', StringUtil.PadType.LEFT));
        model.addAttribute("receipt", receipt);

        Customer lastCustomer = customerService.findLastCustomer();
        long lastCustomerId = lastCustomer == null?0:lastCustomer.getId();
        String customerCode = "KTD" + String.valueOf(lastCustomerId + 1);
        model.addAttribute("customerCode", customerCode);
        List<Customer> customers = customerService.findAllByCustomerType(2);
        model.addAttribute("customers", customers);
        List<Medicine> medicines = medicineService.getAll();
        model.addAttribute("medicines", medicines);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails auth = (UserDetails) principal;

        return "/receipt/order_receipt/create";
    }
}
