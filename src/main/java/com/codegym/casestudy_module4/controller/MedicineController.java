package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.dto.MedicineDTO;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.service.impl.MedicineGroupService;
import com.codegym.casestudy_module4.service.impl.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineGroupService medicineGroupService;

    @GetMapping("/list")
    public String showList(Model model) {
        try {
            List<Medicine> listMedicines = medicineService.getAll();
            model.addAttribute("listMedicines", listMedicines);
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "Gặp lỗi trong quá trình truy xuất database");
        }
        return "medicine/list";
    }

    @GetMapping("/createView")
    public String createMedicine(Model model) {
        List<MedicineGroup> medicineGroups = medicineGroupService.getAll();
        System.out.println(">>>>>>>>>>>" + medicineGroups.size());
        model.addAttribute("medicine", new Medicine());
        model.addAttribute("medicineGroups", medicineGroups);
        return "medicine/createMedicine";
    }

    @PostMapping("/created")
    public String createMedicine(@ModelAttribute("medicine") Medicine medicine, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(">>>>>>>>>>>>>>" + bindingResult.getAllErrors());
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "medicines/createView";
        }

        medicineService.save(medicine);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/medicines/list";
    }

}




