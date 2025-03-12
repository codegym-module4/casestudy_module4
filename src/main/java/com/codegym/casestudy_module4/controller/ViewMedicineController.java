package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.impl.MedicineGroupService;
import com.codegym.casestudy_module4.service.impl.MedicineService;
import com.codegym.casestudy_module4.service.impl.SupplierService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String accessUpdate(Model model, @PathVariable("id") long id) {
        try {
            Medicine medicine = medicineService.findById(id);
            List<MedicineGroup> medicineGroups = medicineGroupService.getAll();
            List<Supplier> suppliers = supplierService.getAll();

            model.addAttribute("medicine", medicine);
            model.addAttribute("medicineGroups", medicineGroups);
            model.addAttribute("suppliers", suppliers);
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "Gặp lỗi trong quá trình truy xuất database");
        }
        return "medicine/updateMedicine";

    }

    @PostMapping("/details/updated")
    public String updateMedicine(@ModelAttribute("medicine") Medicine medicine, HttpServletRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(">>>>>>>>>>>>>>" + bindingResult.getAllErrors());
            model.addAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("message", "Đã có lỗi xảy ra trong quá trình chỉnh sửa. Vui lòng thử lại!");
            return "redirect:medicines/list";
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
        redirectAttributes.addFlashAttribute("message", "Xóa thành công");
        return "redirect:/medicines/list";
    }

}
