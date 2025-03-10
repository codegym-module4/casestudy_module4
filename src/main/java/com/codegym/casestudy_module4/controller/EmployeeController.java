package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public String index(
            Model model,
            @RequestParam(name="name", defaultValue = "") String name,
            @RequestParam(name="sortField", defaultValue = "name") String sortField,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        if (page > 0) {
            page = page - 1;
        }
        Sort sort = Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, 20, sort);
        Page<Employee> employees = employeeService.findByFullNameContainingIgnoreCase(name, pageable);
        if (employees.isEmpty()) {
            model.addAttribute("message", "Not found");
        }
        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@RequestParam Long id, Model model) {
        Employee employee = employeeService.findById(id);
        //ID sẽ luôn tồn tại nên không cần kiểm tra null, do sẽ dùng tick box để chọn
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("/save")
    public String save(Employee employee, RedirectAttributes redirect) {
        employeeService.save(employee);
        redirect.addFlashAttribute("success", "Saved employee successfully!");
        return "redirect:/employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") long id, @ModelAttribute Employee employee) {
        employeeService.update(id, employee);
        return "redirect:/employee";
    }
}
