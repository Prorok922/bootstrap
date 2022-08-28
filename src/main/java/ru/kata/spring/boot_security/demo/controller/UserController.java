package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @GetMapping("/user")
    public String showUserByEmail(Model model, Principal principal) {
        model.addAttribute("userAuth", userService.loadUserByUserEmail(principal.getName()));
        return "userPage";
    }
}
