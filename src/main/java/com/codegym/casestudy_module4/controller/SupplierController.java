package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.ISupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String showList(Model model,
                           @RequestParam(value = "code", defaultValue = "") String code,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(name = "page", defaultValue = "0") int page) {
        // Đảm bảo không bị lỗi khi nhập số trang > 1
        if (page > 0) {
            page = page - 1;
        }

        Pageable pageable = PageRequest.of(page, 5);
        Page<Supplier> suppliers;

        if (!code.isEmpty()) {
            suppliers = supplierService.findByCodeContainingIgnoreCase(code, pageable);
        } else if (!name.isEmpty()){
            suppliers = supplierService.findByNameContainingIgnoreCase(name, pageable);
        } else {
            suppliers = supplierService.findAll(pageable);
        }
        // Lưu lại query params để sử dụng trong phân trang
        String queryParams = "code=" + code + "&name=" + name;

        model.addAttribute("suppliers", suppliers);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("queryParams", queryParams);

        return "supplier/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        supplierService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Xóa thành công");
        return "redirect:/supplier/list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("suppliers", supplierService.getAll());
        return "supplier/create";
    }

    @PostMapping("/create")
    public String createSupplier( @ModelAttribute("supplier") @Valid Supplier supplier,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "supplier/create";
        }
        supplier.setCreatedAt(LocalDateTime.now());
        supplierService.save(supplier);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/supplier/list";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        return "supplier/edit";
    }

    @PostMapping("/edit/{id}")
    public String editSupplier(@ModelAttribute @Valid Supplier supplier, BindingResult bindingResult, @PathVariable("id") long id, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("supplier", supplier);
            return "supplier/edit";
        }
        supplierService.update(id, supplier);
        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thành công");
        return "redirect:/supplier/list";
    }
}
