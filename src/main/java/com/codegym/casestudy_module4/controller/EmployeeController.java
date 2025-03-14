package com.codegym.casestudy_module4.controller;

import com.codegym.casestudy_module4.entity.Employee;
import com.codegym.casestudy_module4.entity.Role;
import com.codegym.casestudy_module4.entity.User;
import com.codegym.casestudy_module4.repository.IRoleRepository;
import com.codegym.casestudy_module4.repository.IUserRepository;
import com.codegym.casestudy_module4.service.IEmployeeService;
import com.codegym.casestudy_module4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(
            Model model,
            @RequestParam(name = "searchInput", defaultValue = "") String searchInput,
            @RequestParam(name = "filterBy", defaultValue = "fullName") String filterBy,
            @RequestParam(name = "sortBy", defaultValue = "fullName") String sortBy,
            @RequestParam(name = "page", defaultValue = "0") int page
    ) {
        if (page > 0) {
            page = page - 1;
        }

        Page<Employee> employees;
        switch (filterBy) {
            case "code":
                employees = employeeService.findByCodeContainingIgnoreCase(searchInput, PageRequest.of(page, 2, Sort.by(sortBy)));
                break;
            case "fullName":
                employees = employeeService.findByFullNameContainingIgnoreCase(searchInput, PageRequest.of(page, 2, Sort.by(sortBy)));
                break;
            case "role":
                employees = employeeService.findByRoleNameContainingIgnoreCase(searchInput, PageRequest.of(page, 2, Sort.by(sortBy)));
                break;
            case "address":
                employees = employeeService.findByAddressContainingIgnoreCase(searchInput, PageRequest.of(page, 2, Sort.by(sortBy)));
                break;
            case "phone":
                employees = employeeService.findByPhoneContainingIgnoreCase(searchInput, PageRequest.of(page, 2, Sort.by(sortBy)));
                break;
            default:
                employees = Page.empty();
        }

        if (employees.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy dữ liệu");
        }

        Map<Long, String> employeeRoles = new HashMap<>();

        for (Employee employee : employees) {
            Long id = employee.getId();
            String roleName = employeeService.getEmployeeRole(id);
            employeeRoles.put(id, roleName);
            System.out.println("Employee ID: " + id + " - Role: " + roleName);
        }

        String queryParams = "searchInput=" + searchInput + "&filterBy=" + filterBy + "&sortBy=" + sortBy;

        model.addAttribute("employeeRoles", employeeRoles);
        model.addAttribute("employees", employees);
        model.addAttribute("searchInput", searchInput);
        model.addAttribute("filterBy", filterBy);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("queryParams", queryParams);

        return "employee/list";
    }

        @GetMapping("/create")
        public String create(Model model) {
            Employee employee = new Employee();
            String newCode = employeeService.generateCode();
            employee.setCode(newCode);
            model.addAttribute("employee", employee);
            return "employee/create";
        }

        @GetMapping("/edit/{id}")
        public String edit (@PathVariable Long id, Model model){
            Employee employee = employeeService.findById(id);
            User user = userRepository.findByEmployeeId(id);
            model.addAttribute("user", user);
            model.addAttribute("employee", employee);
            return "employee/edit";
        }

        @PostMapping("/save")
        public String save (
                @RequestParam(name = "username") String username,
                @RequestParam(name = "role") Long roleID,
                @Validated @ModelAttribute Employee employee,
                BindingResult bindingResult,
                RedirectAttributes redirect,
                Model model){

            if (bindingResult.hasErrors()) {
                model.addAttribute("employee", employee);
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "employee/create";
            }

            employee.setCreatedAt(LocalDateTime.now());
            employeeService.save(employee);

            User user = new User();
            user.setUsername(username);
            user.setEmployee(employee);
            user.setStatus(1);
            user.setCreatedAt(LocalDateTime.now());

            //mã hóa mật khẩu mặc định là 123456789
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode("123456789");
            user.setPassword(encodedPassword);

            //tạo code user tự động column để lưu vào bảng user
            String newCode = userService.generateCode();
            user.setCode(newCode);

            Role role = roleRepository.findById(roleID).orElseThrow(() -> new RuntimeException("Không tìm thấy role"));
            user.setRole(role);
            userRepository.save(user);

            redirect.addFlashAttribute("success", "Đã thêm mới thành công");
            return "redirect:/employee";
        }

        @GetMapping("/delete/{id}")
        public String delete (@PathVariable("id") long id, RedirectAttributes redirectAttributes){
            employeeService.remove(id);
            redirectAttributes.addFlashAttribute("success", "Đã xóa thành công");
            return "redirect:/employee";
        }

        @PostMapping("/edit/{id}")
        public String updateEmployee (
                @PathVariable("id") long id,
                @Validated @ModelAttribute Employee employee,
                BindingResult bindingResult,
                RedirectAttributes redirectAttributes,
                Model model)
        {
            if (bindingResult.hasErrors()) {
                model.addAttribute("user", userRepository.findByEmployeeId(id));
                model.addAttribute("employee", employee);
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "employee/edit";
            }
            employeeService.update(id, employee);
            redirectAttributes.addFlashAttribute("success", "Đã cập nhật thành công");
            return "redirect:/employee";
        }

    }
