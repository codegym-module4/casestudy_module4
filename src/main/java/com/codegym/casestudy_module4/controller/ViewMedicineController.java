package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.impl.MedicineGroupService;
import com.codegym.casestudy_module4.service.impl.MedicineService;
import com.codegym.casestudy_module4.service.impl.SupplierService;
import com.codegym.casestudy_module4.ulti.ValidationMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/medicines")
public class ViewMedicineController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineGroupService medicineGroupService;

    @Autowired
    private SupplierService supplierService;

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
    public String accessUpdate(Model model,
                               @PathVariable("id") long id,
                               @ModelAttribute("listErrorMes") Map<String, String> listErrorMes) {
        try {
            if (listErrorMes == null || listErrorMes.isEmpty()) {
                listErrorMes = new HashMap<>();
            }
            Medicine medicine = medicineService.findById(id);
            List<MedicineGroup> medicineGroups = medicineGroupService.getAll();
            List<Supplier> suppliers = supplierService.getAll();
            model.addAttribute("medicine", medicine);
            model.addAttribute("medicineGroups", medicineGroups);
            model.addAttribute("suppliers", suppliers);
            model.addAttribute("listErrorMes", listErrorMes);
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "Gặp lỗi trong quá trình truy xuất database");
        }
        return "medicine/updateMedicine";

    }

    @PostMapping("/details/updated")
    public String updateMedicine(@Validated @ModelAttribute("medicine") Medicine medicine,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            Map<String, String> listErrorsMes = ValidationMessage.getErrorMes(bindingResult);
            redirectAttributes.addFlashAttribute("listErrorMes", listErrorsMes);
            Medicine exist = medicineService.findByCode(medicine.getCode());
            return "redirect:/medicines/details/update/" + exist.getId();

        }
        Medicine exist = medicineService.findByCode(medicine.getCode());
        medicine.getCode();
        medicineService.update(exist.getId(), medicine);
        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa thành công!");
        return "redirect:/medicines/list";
    }

    @GetMapping("/details/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {
        medicineService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Xóa thành công!");
        return "redirect:/medicines/list";
    }

}
