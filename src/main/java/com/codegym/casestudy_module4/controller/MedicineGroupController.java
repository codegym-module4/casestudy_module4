package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.service.IMedicineGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
