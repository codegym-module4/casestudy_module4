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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/medicines")
public class MedicinesController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineGroupService medicineGroupService;

    @Autowired
    private SupplierService supplierService;


    @GetMapping("/list")
    public String showList(Model model,
                           @RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam Map<String, String> search,
                           @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        try {
            Page<Medicine> listMedicines = medicineService.findByName(name, PageRequest.of(page - 1, 6));
            search.remove("page");
            String queryParams = search.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            model.addAttribute("page", page - 1);
            model.addAttribute("queryParams", queryParams);
            model.addAttribute("listMedicines", listMedicines);
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "Gặp lỗi trong quá trình truy xuất database");
            return "medicine/listMedicine";
        }
        return "medicine/listMedicine";
    }

    @GetMapping("/search/{query}/{value}")
    public String searchMedicine(Model model,
                                 @PathVariable("query") String query,
                                 @PathVariable("value") String value,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request,
                                 @RequestParam(name = "page", defaultValue = "1") int page

    ) {
        try {

            Pageable pageable = PageRequest.of(page - 1, 5);
            String url = request.getRequestURI();
            switch (query) {
                case "name":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByName(pageable, value);
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "code":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByCode(pageable, value);
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                case "group":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByGroup(pageable, value);
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "ingredients":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByIngredients(pageable, value);
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "min_import_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByImportPriceMin(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "min_retail_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByRetailPriceMin(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "min_wholesale_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByWholesalePriceMin(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;

                case "max_import_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByImportPriceMax(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "max_retail_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByRetailPriceMax(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "max_wholesale_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByWholesalePriceMax(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;

                case "equal_import_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByImportPriceEqual(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "equal_retail_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByRetailPriceEqual(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                case "equal_wholesale_price":
                    if (!value.isEmpty()) {
                        Page<Medicine> listMedicines = medicineService.filterByWholesalePriceEqual(pageable, Integer.parseInt(value));
                        int totalPages = listMedicines.getTotalPages();
                        if (page >= totalPages) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Trang bạn yêu cầu vượt quá số trang hiện có!");
                            return "redirect:/medicines/list";
                        }
                        if (listMedicines.isEmpty()) {
                            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                            return "redirect:/medicines/list";
                        }
                        model.addAttribute("queryParams", "");
                        model.addAttribute("url", url);
                        model.addAttribute("listMedicines", listMedicines);
                    }
                    break;
                default:
                    redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy kết quả phù hợp!");
                    return "redirect:/medicines/list";
            }
            model.addAttribute("message", "Tìm kiếm thành công!");
            return "medicine/listMedicine";
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Đã có lỗi xảy ra. Vui lòng thử lại!");
            return "redirect:/medicines/list";
        }

    }


    @GetMapping("/createView")
    public String createMedicine(Model model, @ModelAttribute("listErrorMes") List<String> listErrorMes) {
        List<MedicineGroup> medicineGroups = medicineGroupService.getAll();
        List<Supplier> suppliers = supplierService.getAll();
        model.addAttribute("medicine", new Medicine());
        model.addAttribute("medicineGroups", medicineGroups);
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("listErrorMes", listErrorMes);
        return "medicine/createMedicine";

    }

    @PostMapping("/created")
    public String createMedicine(
            @Validated @ModelAttribute("medicine") Medicine medicine, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(">>>>>>>>>>>>>>" + bindingResult.getAllErrors());
            List<String> listErrorsMes = ValidationMessage.getErrorMessages(bindingResult);
            System.out.println("++++++++++++++++++++++++" + listErrorsMes);
            redirectAttributes.addFlashAttribute("listErrorMes", listErrorsMes);
//            model.addAttribute("listErrorMes", listErrorsMes);
//            model.addAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/medicines/createView";
        }
        medicine.setCreatedAt(LocalDateTime.now());
        medicine.setStatus("Available");
        medicineService.save(medicine);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công!");
        return "redirect:/medicines/list";
    }


}




