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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                employees = employeeService.findByCodeContainingIgnoreCase(searchInput, PageRequest.of(page, 5, Sort.by(sortBy)));
                break;
            case "fullName":
                employees = employeeService.findByFullNameContainingIgnoreCase(searchInput, PageRequest.of(page, 5, Sort.by(sortBy)));
                break;
            case "role":
                employees = employeeService.findByRoleNameContainingIgnoreCase(searchInput, PageRequest.of(page, 5, Sort.by(sortBy)));
                break;
            case "address":
                employees = employeeService.findByAddressContainingIgnoreCase(searchInput, PageRequest.of(page, 5, Sort.by(sortBy)));
                break;
            case "phone":
                employees = employeeService.findByPhoneContainingIgnoreCase(searchInput, PageRequest.of(page, 5, Sort.by(sortBy)));
                break;
            default:
                employees = Page.empty();
        }

        if (employees.isEmpty()) {
            model.addAttribute("message", "Not found");
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
                @ModelAttribute Employee employee,
                RedirectAttributes redirect){

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

            Role role = roleRepository.findById(roleID).orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
            userRepository.save(user);

            redirect.addFlashAttribute("success", "Saved employee and user successfully!");
            return "redirect:/employee";
        }

        @GetMapping("/delete/{id}")
        public String delete (@PathVariable("id") long id){
            employeeService.remove(id);
            return "redirect:/employee";
        }

        @PostMapping("/edit/{id}")
        public String updateEmployee ( @PathVariable("id") long id, @ModelAttribute Employee employee ){
            employeeService.update(id, employee);
            return "redirect:/employee";
        }

    }
