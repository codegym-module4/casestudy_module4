package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.entity.Supplier;
import com.codegym.casestudy_module4.service.impl.MedicineGroupService;
import com.codegym.casestudy_module4.service.impl.MedicineService;
import com.codegym.casestudy_module4.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/medicines")
public class MedicineController {
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
                           @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        try {
            Page<Medicine> listMedicines = medicineService.findByName(name, PageRequest.of(page, 5));
            search.remove("page");
            String queryParams = search.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            model.addAttribute("queryParams", queryParams);
            model.addAttribute("listMedicines", listMedicines);
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "Gặp lỗi trong quá trình truy xuất database");
        }
        return "medicine/listMedicine";
    }

    @GetMapping("/search")
    public String searchMedicine(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "name", defaultValue = "") String name,
                                 @RequestParam Map<String, String> search,
                                 @RequestParam("name") Optional<String> nameOP,
                                 @RequestParam("code") Optional<String> codeOP,
                                 @RequestParam("group") Optional<String> groupOP,
                                 @RequestParam("ingredients") Optional<String> ingredientsOP,
                                 @RequestParam("min_import_price") Optional<String> min_import_price,
                                 @RequestParam("min_retail_price") Optional<String> min_retail_price,
                                 @RequestParam("min_wholesale_price") Optional<String> min_wholesale_price,
                                 @RequestParam("max_import_price") Optional<String> max_import_price,
                                 @RequestParam("max_retail_price") Optional<String> max_retail_price,
                                 @RequestParam("max_wholesale_price") Optional<String> max_wholesale_price,
                                 @RequestParam("equal_import_price") Optional<String> equal_import_price,
                                 @RequestParam("equal_retail_price") Optional<String> equal_retail_price,
                                 @RequestParam("equal_wholesale_price") Optional<String> equal_wholesale_price
    ) {
        try {

            String name_op = nameOP.isPresent() ? nameOP.get() : "";
            String code_op = codeOP.isPresent() ? codeOP.get() : "";
            String group_op = groupOP.isPresent() ? groupOP.get() : "";
            String ingredients_op = ingredientsOP.isPresent() ? ingredientsOP.get() : "";

            int min_importPrice_op = min_import_price.isPresent() ? Integer.parseInt(min_import_price.get()) : 0;
            int min_retailPrice_op = min_retail_price.isPresent() ? Integer.parseInt(min_retail_price.get()) : 0;
            int min_wholesalePrice_op = min_wholesale_price.isPresent() ? Integer.parseInt(min_wholesale_price.get()) : 0;

            int max_importPrice_op = max_import_price.isPresent() ? Integer.parseInt(max_import_price.get()) : 0;
            int max_retailPrice_op = max_retail_price.isPresent() ? Integer.parseInt(max_retail_price.get()) : 0;
            int max_wholesalePrice_op = max_wholesale_price.isPresent() ? Integer.parseInt(max_wholesale_price.get()) : 0;

            int equal_importPrice_op = equal_import_price.isPresent() ? Integer.parseInt(equal_import_price.get()) : 0;
            int equal_retailPrice_op = equal_retail_price.isPresent() ? Integer.parseInt(equal_retail_price.get()) : 0;
            int equal_wholesalePrice_op = equal_wholesale_price.isPresent() ? Integer.parseInt(equal_wholesale_price.get()) : 0;

            Pageable pageable = PageRequest.of(page, 5);
            for (Map.Entry<String, String> entry : search.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value.isEmpty()) {
                    return "redirect:/medicines/list";
                }

                switch (key) {
                    case "name":
                        if (!name_op.isEmpty()) {
                            Page<Medicine> listMedicines = medicineService.filterByName(pageable, name_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "code":
                        if (!code_op.isEmpty()) {
                            Page<Medicine> listMedicines = medicineService.filterByCode(pageable, code_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                    case "group":
                        if (!group_op.isEmpty()) {
                            Page<Medicine> listMedicines = medicineService.filterByGroup(pageable, group_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "ingredients":
                        if (!ingredients_op.isEmpty()) {
                            Page<Medicine> listMedicines = medicineService.filterByIngredients(pageable, ingredients_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "min_import_price":
                        if (min_importPrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByImportPriceMin(pageable, min_importPrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "min_retail_price":
                        if (min_retailPrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByRetailPriceMin(pageable, min_retailPrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "min_wholesale_price":
                        if (min_wholesalePrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByWholesalePriceMin(pageable, min_wholesalePrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;

                    case "max_import_price":
                        if (max_importPrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByImportPriceMax(pageable, max_importPrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "max_retail_price":
                        if (max_retailPrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByRetailPriceMax(pageable, max_retailPrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "max_wholesale_price":
                        if (max_wholesalePrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByWholesalePriceMax(pageable, max_wholesalePrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;

                    case "equal_import_price":
                        if (equal_importPrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByImportPriceEqual(pageable, equal_importPrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "equal_retail_price":
                        if (equal_retailPrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByRetailPriceEqual(pageable, equal_retailPrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;
                    case "equal_wholesale_price":
                        if (equal_wholesalePrice_op != 0) {
                            Page<Medicine> listMedicines = medicineService.filterByWholesalePriceEqual(pageable, equal_wholesalePrice_op);
                            model.addAttribute("listMedicines", listMedicines);
                        }
                        break;

                    default:
                        return "redirect:/medicines/list";

                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching medicines: " + e.getMessage());
            model.addAttribute("errorMessage", "Gặp lỗi trong quá trình truy xuất database");
        }
        return "medicine/listMedicine";
    }


    @GetMapping("/createView")
    public String createMedicine(Model model) {
        List<MedicineGroup> medicineGroups = medicineGroupService.getAll();
        List<Supplier> suppliers = supplierService.getAll();

        model.addAttribute("medicine", new Medicine());
        model.addAttribute("medicineGroups", medicineGroups);
        model.addAttribute("suppliers", suppliers);

        return "medicine/createMedicine";
    }

    @PostMapping("/created")
    public String createMedicine(@ModelAttribute("medicine") Medicine medicine, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(">>>>>>>>>>>>>>" + bindingResult.getAllErrors());
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "medicines/createView";
        }
        medicine.setCreatedAt(LocalDateTime.now());
        medicineService.save(medicine);
        redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/medicines/list";
    }


}




