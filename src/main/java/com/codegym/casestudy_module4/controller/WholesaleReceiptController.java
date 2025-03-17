package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.dto.MedicineDTO;
import com.codegym.casestudy_module4.dto.OrderReceiptDTO;
import com.codegym.casestudy_module4.dto.WholesaleReceiptDTO;
import com.codegym.casestudy_module4.entity.*;
import com.codegym.casestudy_module4.service.*;
import com.codegym.casestudy_module4.ulti.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/receipt/wholesale")
public class WholesaleReceiptController {

    @Autowired
    private IReceiptService receiptService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IMedicineService medicineService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReceiptDetailService receiptDetailService;

    @GetMapping("/create")
    public String create(
            Model model
    ) {
        WholesaleReceiptDTO receipt = new WholesaleReceiptDTO();
        Receipt lastReceipt = receiptService.findLastReceipt();
        long lastReceiptId = lastReceipt == null?0:lastReceipt.getId();
        receipt.setCode("HD" + StringUtil.strPad(String.valueOf(lastReceiptId + 1), 5, '0', StringUtil.PadType.LEFT));

        Customer lastCustomer = customerService.findLastCustomer();
        long lastCustomerId = lastCustomer == null?0:lastCustomer.getId();
        String customerCode = "KSI" + String.valueOf(lastCustomerId + 1);
        Customer newCustomer = new Customer();
        newCustomer.setCode(customerCode);
        newCustomer.setCustomerType(3);
        List<Customer> customers = customerService.findAllByCustomerType(3);
        List<Medicine> medicines = medicineService.getAll();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        receipt.setEmployee(user.getEmployee());
        model.addAttribute("receipt", receipt);
        model.addAttribute("customer", newCustomer);
        model.addAttribute("customerCode", customerCode);
        model.addAttribute("customers", customers);
        model.addAttribute("medicines", medicines);

        return "/receipt/wholesale/create";
    }

    @PostMapping("/create")
    public String create(
            @Validated @ModelAttribute("receipt") WholesaleReceiptDTO receipt,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if(bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error -> {
//                errors.put(error.getField(), error.getDefaultMessage());
//            });
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("receipt", receipt);
            List<Customer> customers = customerService.findAllByCustomerType(2);
            List<Medicine> medicines = medicineService.getAll();
            model.addAttribute("customers", customers);
            model.addAttribute("medicines", medicines);
            Customer lastCustomer = customerService.findLastCustomer();
            long lastCustomerId = lastCustomer == null?0:lastCustomer.getId();
            String customerCode = "KSI" + String.valueOf(lastCustomerId + 1);
            Customer newCustomer = new Customer();
            newCustomer.setCode(customerCode);
            newCustomer.setCustomerType(3);
            model.addAttribute("customer", newCustomer);

            return "/receipt/wholesale/create";
        }
        Receipt newReceipt = new Receipt(
                receipt.getCode(),
                receipt.getCustomer(),
                receipt.getEmployee(),
                receipt.getNote(),
                3,
                1,
                receipt.getCreatedAt()
        );
        newReceipt = receiptService.updateOrCreate(newReceipt);
        List<MedicineDTO> list = receipt.getItems();
        for (MedicineDTO medicineDTO : list) {
            ReceiptDetail detail = new ReceiptDetail(newReceipt, medicineDTO.getMedicine(), medicineDTO.getUnit(), medicineDTO.getPrice(), medicineDTO.getQuantity());
            receiptDetailService.updateOrCreate(detail);
        }
        redirectAttributes.addFlashAttribute("message", "Thêm mới hóa đơn thành công");

        return "redirect:/receipt";
    }
    @GetMapping("/edit/{id}")
    public String edit(
            Model model,
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        Receipt receipt = receiptService.findById(id);
        if (receipt == null) {
            redirectAttributes.addFlashAttribute("messageError", "Hóa đơn không tồn tại");

            return "redirect:/receipt";
        }
        List<ReceiptDetail> list = receiptDetailService.getReceiptDetailsByReceiptId(id);
        List<MedicineDTO> items = new ArrayList<>();
        for (ReceiptDetail receiptDetail : list) {
            MedicineDTO medicineDTO = new MedicineDTO(receiptDetail.getId(), receiptDetail.getMedicine(), receiptDetail.getUnit(), receiptDetail.getQuantity(), receiptDetail.getPrice());
            items.add(medicineDTO);
        }
        WholesaleReceiptDTO receiptDTO = new WholesaleReceiptDTO(
                receipt.getId(),
                receipt.getCode(),
                receipt.getCustomer(),
                receipt.getEmployee(),
                receipt.getNote(),
                receipt.getCreatedAt(),
                items
        );

        Customer lastCustomer = customerService.findLastCustomer();
        long lastCustomerId = lastCustomer == null?0:lastCustomer.getId();
        String customerCode = "KTD" + String.valueOf(lastCustomerId + 1);
        List<Customer> customers = customerService.findAllByCustomerType(3);
        List<Medicine> medicines = medicineService.getAll();
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userService.findByUsername(username);
//        receipt.setEmployee(user.getEmployee());
        model.addAttribute("receipt", receiptDTO);
        model.addAttribute("customerCode", customerCode);
        model.addAttribute("customers", customers);
        model.addAttribute("medicines", medicines);
        Customer newCustomer = new Customer();
        newCustomer.setCode(customerCode);
        newCustomer.setCustomerType(3);
        model.addAttribute("customer", newCustomer);

        return "/receipt/wholesale/edit";
    }

    @PostMapping("/edit")
    public String edit(
            @Validated @ModelAttribute("receipt") WholesaleReceiptDTO receipt,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("receipt", receipt);
            List<Customer> customers = customerService.findAllByCustomerType(3);
            List<Medicine> medicines = medicineService.getAll();
            model.addAttribute("customers", customers);
            model.addAttribute("medicines", medicines);
            Customer lastCustomer = customerService.findLastCustomer();
            long lastCustomerId = lastCustomer == null?0:lastCustomer.getId();
            String customerCode = "KSI" + String.valueOf(lastCustomerId + 1);
            Customer newCustomer = new Customer();
            newCustomer.setCode(customerCode);
            newCustomer.setCustomerType(3);
            model.addAttribute("customer", newCustomer);
            return "/receipt/wholesale/edit";
        }
        Receipt dataReceipt = new Receipt(
                receipt.getCode(),
                receipt.getCustomer(),
                receipt.getEmployee(),
                receipt.getNote(),
                3,
                1,
                receipt.getCreatedAt()
        );
        dataReceipt.setId(receipt.getId());
        dataReceipt = receiptService.updateOrCreate(dataReceipt);
        List<MedicineDTO> list = receipt.getItems();
        List<ReceiptDetail> receiptDetails = receiptDetailService.getReceiptDetailsByReceiptId(dataReceipt.getId());
        List<ReceiptDetail> newReceiptDetails = new ArrayList<>();
        for (MedicineDTO medicineDTO : list) {
            if (medicineDTO.getMedicine() != null ) {
                ReceiptDetail detail = new ReceiptDetail(medicineDTO.getId(), dataReceipt, medicineDTO.getMedicine(), medicineDTO.getUnit(), medicineDTO.getPrice(), medicineDTO.getQuantity());
                ReceiptDetail resDetail = receiptDetailService.updateOrCreate(detail);
                newReceiptDetails.add(resDetail);
            }
        }
        Set<Long> set2 = newReceiptDetails.stream()
                .map(ReceiptDetail::getId) // Lấy danh sách ID từ list2
                .collect(Collectors.toSet());
        List<ReceiptDetail> result = receiptDetails.stream()
                .filter(temp -> !set2.contains(temp.getId()))
                .collect(Collectors.toList());
        receiptDetailService.deleteAllReceiptDetails(result);

        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa hóa đơn thành công");

        return "redirect:/receipt";
    }

}