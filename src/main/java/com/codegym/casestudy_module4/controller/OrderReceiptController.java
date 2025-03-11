package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.dto.OrderReceiptDTO;
import com.codegym.casestudy_module4.entity.*;
import com.codegym.casestudy_module4.service.ICustomerService;
import com.codegym.casestudy_module4.service.IMedicineService;
import com.codegym.casestudy_module4.service.IReceiptService;
import com.codegym.casestudy_module4.service.IUserService;
import com.codegym.casestudy_module4.ulti.StringUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
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

    @Autowired
    private IUserService userService;

    @GetMapping("/create")
    public String create(
        Model model
    ) {
        OrderReceiptDTO receipt = new OrderReceiptDTO();
        Receipt lastReceipt = receiptService.findLastReceipt();
        long lastReceiptId = lastReceipt == null?0:lastReceipt.getId();
        receipt.setCode("HD" + StringUtil.strPad(String.valueOf(lastReceiptId + 1), 5, '0', StringUtil.PadType.LEFT));

        Customer lastCustomer = customerService.findLastCustomer();
        long lastCustomerId = lastCustomer == null?0:lastCustomer.getId();
        String customerCode = "KTD" + String.valueOf(lastCustomerId + 1);
        List<Customer> customers = customerService.findAllByCustomerType(2);
        List<Medicine> medicines = medicineService.getAll();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        receipt.setEmployee(user.getEmployee());
        model.addAttribute("receipt", receipt);
        model.addAttribute("customerCode", customerCode);
        model.addAttribute("customers", customers);
        model.addAttribute("medicines", medicines);

        return "/receipt/order_receipt/create";
    }

    @PostMapping("/create")
    public String create(
        @Validated @ModelAttribute("receipt") OrderReceiptDTO receipt,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        if(bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error -> {
//                errors.put(error.getField(), error.getDefaultMessage());
//            });
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("receipt", receipt);
            List<Customer> customers = customerService.findAllByCustomerType(2);
            List<Medicine> medicines = medicineService.getAll();
            model.addAttribute("customers", customers);
            model.addAttribute("medicines", medicines);
            return "/receipt/order_receipt/create";
        }
        Receipt newReceipt = new Receipt(
                receipt.getCode(),
                receipt.getCustomer(),
                receipt.getEmployee(),
                receipt.getSymptoms(),
                receipt.getDiagnose(),
                receipt.getDoctor(),
                receipt.getDoctor_address(),
                2,
                1,
                receipt.getCreatedAt()
        );
//        newReceipt = receiptService.updateOrCreate(newReceipt);

        return "redirect:/receipt";
    }
}
