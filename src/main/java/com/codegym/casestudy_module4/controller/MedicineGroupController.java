package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.service.IMedicineGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/medicinegroup")
public class MedicineGroupController {

    @Autowired
    private IMedicineGroupService medicineGroupService;

    @GetMapping("/list")
    public String showList(Model model){
        List<MedicineGroup> medicineGroups = medicineGroupService.getAll();
        model.addAttribute("medicineGroups", medicineGroups);
        return "medicinegroup/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteMedicineGroup(@PathVariable("id") Long id,
                                      RedirectAttributes redirectAttributes){
        medicineGroupService.remove(id);
        redirectAttributes.addFlashAttribute("message","Delete successfully");
        return "redirect:/medicinegroup/list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("medicineGroup", new MedicineGroup());
        model.addAttribute("medicineGroups" ,medicineGroupService.getAll());
        return "medicinegroup/create";
    }

    @PostMapping("/create")
    public String createMedicineGroup(@ModelAttribute("medicineGroup") MedicineGroup medicineGroup,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            return "medicinegroup/create";
        }
        medicineGroup.setCreatedAt(LocalDateTime.now());
        medicineGroupService.save(medicineGroup);
        redirectAttributes.addFlashAttribute("message","Create medicine group successfully");
        return "redirect:/medicinegroup/list";
    }
}
