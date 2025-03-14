package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Receipt;
import com.codegym.casestudy_module4.service.IReceiptService;
import com.codegym.casestudy_module4.ulti.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private IReceiptService receiptService;

    @GetMapping
    public String index(
            Model model,
            @RequestParam Map<String, String> search,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        if (page > 0) {
            page = page - 1;
        }
        String sortBy = "createdAt";
        if (search.containsKey("sortBy") && !search.get("sortBy").isEmpty()) {
            sortBy = search.get("sortBy");
        }
        Sort sort = Sort.by(sortBy).descending();
        Page<Receipt> receipts = receiptService.getReceipt(search, PageRequest.of(page, 20, sort));
        search.remove("page");
        String queryParams = search.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        model.addAttribute("receipts", receipts);
        model.addAttribute("search", search);
        model.addAttribute("receipt_type", GlobalConstants.RECEIPT_TYPES);
        model.addAttribute("receipt_sortBy", GlobalConstants.RECEIPT_SORTBY);
        model.addAttribute("queryParams", queryParams);

        return "receipt/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteReceipt(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        receiptService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa hóa đơn thành công!");
        return "redirect:/receipt";
    }
}
