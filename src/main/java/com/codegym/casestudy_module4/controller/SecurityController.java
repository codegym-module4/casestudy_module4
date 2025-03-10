package com.codegym.casestudy_module4.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
//    @GetMapping("/login")
//    public String showPageLogin(@CookieValue(name = "username",  defaultValue = "") String username,
//                                @CookieValue(name = "password", defaultValue = "") String password,
//                                Model model) {
//        model.addAttribute("username", username);
//        model.addAttribute("password", password);
//        return "security/login";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password,
//                        @RequestParam(required = false, name = "remember-me") String rememberMe,
//                        HttpServletResponse response) {
////        Xử lý nghiệp vụ liên quan đến đăng nhập => Spring security
////        Giả sử đăng nhập thành công
//        if("on".equals(rememberMe)) {
//            Cookie cookie = new Cookie("username", username);
//            Cookie cookie1 = new Cookie("password", password);
//            cookie.setMaxAge(60*60*24);
//            cookie1.setMaxAge(60*60*24);
//            response.addCookie(cookie);
//            response.addCookie(cookie1);
//        }
//        return "redirect:/student";
//    }

    @GetMapping(value = "/login")
    public String loginPage(Model model, @RequestParam(value = "error", defaultValue = "")String error) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!username.equals("anonymousUser")) {
            return "redirect:/";
        }
        model.addAttribute("error", error);
        return "login/login";
    }
}
