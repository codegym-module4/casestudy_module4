package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/list")
    public String showList(Model model){
        List<Supplier> suppliers = supplierService.getAll();
        model.addAttribute("suppliers", suppliers);
        return "supplier/list";
    }
}
