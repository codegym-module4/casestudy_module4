package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
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

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("suppliers" ,supplierService.getAll());
        return "supplier/create";
    }


    @PostMapping("/create")
    public String createSupplier(@ModelAttribute("supplier") Supplier supplier,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            return "supplier/create";
        }
        supplier.setCreatedAt(LocalDateTime.now());
        supplierService.save(supplier);
        redirectAttributes.addFlashAttribute("message","Create medicine group successfully");
        return "redirect:/supplier/list";
    }
}
