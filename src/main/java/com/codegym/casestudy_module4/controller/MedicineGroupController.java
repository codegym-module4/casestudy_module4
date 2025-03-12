package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.service.IMedicineGroupService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String showList(Model model,  @RequestParam(value = "name", defaultValue = "")String name,  @RequestParam(value = "code", defaultValue = "")String code) {
        if (!name.equals("")){
            List<MedicineGroup> medicineGroups = medicineGroupService.findByName(name);
            model.addAttribute("medicineGroups", medicineGroups);
            return "medicinegroup/list";
        } else if (!code.equals("")){
            List<MedicineGroup> medicineGroups = medicineGroupService.findByCode(code);
            model.addAttribute("medicineGroups", medicineGroups);
            return "medicinegroup/list";
        }
        List<MedicineGroup> medicineGroups = medicineGroupService.getAll();
        model.addAttribute("medicineGroups", medicineGroups);
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
    public String createMedicineGroup(@ModelAttribute("medicineGroup") MedicineGroup medicineGroup,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "medicinegroup/create";
        }
        medicineGroup.setCreatedAt(LocalDateTime.now());
        medicineGroupService.save(medicineGroup);
        redirectAttributes.addFlashAttribute("message", "Create medicine group successfully");
        return "redirect:/medicinegroup/list";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        MedicineGroup medicineGroup = medicineGroupService.findById(id);
        model.addAttribute("medicineGroup", medicineGroup);
        return "medicinegroup/edit";
    }


    @PostMapping("/edit/{id}")
    public String editMedicineGroup ( @PathVariable("id") long id, @ModelAttribute MedicineGroup medicineGroup ){
        medicineGroupService.update(id, medicineGroup);
        return "redirect:/medicinegroup/list";
    }

}
