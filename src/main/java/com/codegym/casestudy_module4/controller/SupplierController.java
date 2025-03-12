package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/list")
    public String showList(Model model, @RequestParam(value = "name", defaultValue = "")String name, @RequestParam(value = "code", defaultValue = "")String code) {
        if (!name.equals("")){
            List<Supplier> suppliers = supplierService.findByName(name);
            model.addAttribute("suppliers", suppliers);
            return "supplier/list";
        } else if (!code.equals("")){
            List<Supplier> suppliers = supplierService.findByCode(code);
            model.addAttribute("suppliers", suppliers);
            return "supplier/list";
        }
        List<Supplier> suppliers = supplierService.getAll();
        model.addAttribute("suppliers", suppliers);
        return "supplier/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id) {
        supplierService.remove(id);
        return "redirect:/supplier/list";
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
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "supplier/create";
        }
        supplier.setCreatedAt(LocalDateTime.now());
        supplierService.save(supplier);
        redirectAttributes.addFlashAttribute("message", "Create successfully");
        return "redirect:/supplier/list";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        return "supplier/edit";
    }

    @PostMapping("/edit/{id}")
    public String editSupplier(@PathVariable("id") Long id, @ModelAttribute Supplier supplier){
        supplierService.update(id, supplier);
        return "redirect:/supplier/list";
    }
}
