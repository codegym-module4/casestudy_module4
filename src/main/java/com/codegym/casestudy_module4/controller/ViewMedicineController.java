package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.service.impl.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medicines")
public class ViewMedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping(value = "/details/{id_medicine}")
    public String showView(Model model, @PathVariable("id_medicine") long id) {
        try {
            Medicine medicine = medicineService.findById(id);
            if (medicine == null) {
                model.addAttribute("message", "Thuốc không tồn tại trên hệ thống");
            }
            model.addAttribute("medicine", medicine);
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "Gặp lỗi trong quá trình truy xuất database");
        }
        return "medicine/detailMedicine";
    }

    @GetMapping(value = "/details/update/{id}")
    public String accessUpdate(Model model, @PathVariable("id") long id) {
        try {
            Medicine medicine = medicineService.findById(id);
            model.addAttribute("medicine", medicine);
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "Gặp lỗi trong quá trình truy xuất database");
        }
        return "medicine/updateMedicine";

    }

}
