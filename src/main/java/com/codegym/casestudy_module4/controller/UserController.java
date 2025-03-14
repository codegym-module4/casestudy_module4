package com.codegym.casestudy_module4.controller;


import com.codegym.casestudy_module4.entity.User;
import com.codegym.casestudy_module4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public String index(
            Model model,
            @RequestParam(name = "searchInput", defaultValue = "") String searchInput,
            @RequestParam(name = "filterBy", defaultValue = "userName") String filterBy,
            @RequestParam(name = "page" , defaultValue = "0") int page
        ) {
        if (page > 0) {
            page = page - 1;
        }

        Page<User> users;
        switch (filterBy) {
            case "code":
                users = userService.findByCodeContainingIgnoreCase(searchInput, PageRequest.of(page, 2));
                break;
            case "userName":
                users = userService.findByUsernameContainingIgnoreCase(searchInput, PageRequest.of(page, 2));
                break;
            case "employeeFullName":
                users = userService.findByEmployeeFullName(searchInput, PageRequest.of(page, 2));
                break;
            case "roleName":
                users = userService.findByRoleName(searchInput, PageRequest.of(page, 2));
                break;
            default:
                users = Page.empty();
        }

        if (users.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy dữ liệu");
        }

        String queryParams = "searchInput=" + searchInput + "&filterBy=" + filterBy;


        model.addAttribute("users", users);
        model.addAttribute("searchInput", searchInput);
        model.addAttribute("filterBy", filterBy);
        model.addAttribute("queryParams", queryParams);

        return "user/list";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @GetMapping("/changePassword/{id}")
    public String changePassword(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/changePassword";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(
            @PathVariable Long id,
            @ModelAttribute User user,
            @RequestParam (name = "role") String role,
            RedirectAttributes redirectAttributes) {
        user.getRole().setId(Long.parseLong(role));
        userService.update(id, user);
        redirectAttributes.addFlashAttribute("success", "Đã cập nhật thành công");
        return "redirect:/user";
    }

    @PostMapping("/changePassword/{id}")
    public String changePassword(
            @PathVariable Long id,
            @RequestParam(name = "oldPassword") String oldPassword,
            @RequestParam(name = "newPassword1") String newPassword1,
            @RequestParam(name = "newPassword2") String newPassword2,
            RedirectAttributes redirectAttributes) {

        User user = userService.findById(id);
        String currentPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isMatch = encoder.matches(oldPassword, currentPassword);
        String encoded_newPassword1 = encoder.encode(newPassword1);

        if (!isMatch) {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu cũ không đúng");
            return "redirect:/user/edit/" + id;
        } else if (encoder.matches(newPassword1, currentPassword)) {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu mới không được trùng với mật khẩu cũ");
            return "redirect:/user/edit/" + id;
        } else if (!newPassword1.equals(newPassword2)) {
            redirectAttributes.addFlashAttribute("error", "Mật khẩu mới không trùng khớp");
            return "redirect:/user/edit/" + id;
        } else {
            user.setPassword(encoded_newPassword1);
        }
        userService.update(id, user);
        redirectAttributes.addFlashAttribute("success", "Đã cập nhật mật khẩu thành công");
        return "redirect:/user/edit/" + id;
    }
}
