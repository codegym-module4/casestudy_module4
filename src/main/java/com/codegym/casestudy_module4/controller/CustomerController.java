package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    // 1. Hiển thị danh sách khách hàng
    @GetMapping("")
    public String showList(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @PostMapping("/create")
    public String create(
            @Validated @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("customer", customer);

            return "customer/create";
        }

        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("customer", customer);

            return "customer/edit";
        }

        customerService.update(customer.getId(), customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        customerService.remove(id);
        return "redirect:/customers";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "customer/detail";
    }


    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Customer> customers = customerService.findByName(keyword);
        model.addAttribute("customers", customers);
        return "customer/list";
    }
}
