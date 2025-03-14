package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.IMedicineGroupService;
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
@RequestMapping("/medicinegroup")
public class MedicineGroupController {

    @Autowired
    private IMedicineGroupService medicineGroupService;


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
        Page<MedicineGroup> medicineGroups;

        if (!code.isEmpty()) {
            medicineGroups = medicineGroupService.findByCodeContainingIgnoreCase(code, pageable);
        } else if (!name.isEmpty()){
            medicineGroups = medicineGroupService.findByNameContainingIgnoreCase(name, pageable);
        } else {
            medicineGroups = medicineGroupService.findAll(pageable);
        }
        // Lưu lại query params để sử dụng trong phân trang
        String queryParams = "code=" + code + "&name=" + name;

        model.addAttribute("medicineGroups", medicineGroups);
        model.addAttribute("code", code);
        model.addAttribute("name", name);
        model.addAttribute("queryParams", queryParams);

        return "medicinegroup/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicineGroup(@PathVariable("id") Long id) {
        medicineGroupService.remove(id);
        return "redirect:/medicinegroup/list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("medicineGroup", new MedicineGroup());
        model.addAttribute("medicineGroups", medicineGroupService.getAll());
        return "medicinegroup/create";
    }

    @PostMapping("/create")
    public String createMedicineGroup(@ModelAttribute("medicineGroup") @Valid MedicineGroup medicineGroup,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "medicinegroup/create";
        }
        medicineGroup.setCreatedAt(LocalDateTime.now());
        medicineGroupService.save(medicineGroup);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/medicinegroup/list";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        MedicineGroup medicineGroup = medicineGroupService.findById(id);
        model.addAttribute("medicineGroup", medicineGroup);
        return "medicinegroup/edit";
    }


    @PostMapping("/edit/{id}")
    public String editMedicineGroup ( @ModelAttribute @Valid MedicineGroup medicineGroup, BindingResult bindingResult, @PathVariable("id") long id, RedirectAttributes redirectAttributes,  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("medicineGroup", medicineGroup);
            return "medicinegroup/edit";
        }
        medicineGroupService.update(id, medicineGroup);
        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thành công");
        return "redirect:/medicinegroup/list";
    }

}
