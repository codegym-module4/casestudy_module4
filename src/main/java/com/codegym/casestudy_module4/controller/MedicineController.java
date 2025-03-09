package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.service.impl.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/medicines")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @GetMapping("/list")
    public String showList(Model model) {
        try {
            List<Medicine> listMedicines = medicineService.getAll();
            model.addAttribute("listMedicines", listMedicines);
            System.out.println(listMedicines);
        } catch (Exception e) {
            // Log the exception and add an error message to the model
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "An error occurred while fetching the medicines.");
        }
        return "medicine/list";
    }


}



