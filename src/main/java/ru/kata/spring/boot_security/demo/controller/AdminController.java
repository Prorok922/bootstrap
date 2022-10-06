package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping
    public String showAdminRootPage(Model model, Principal principal) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("userAuth", userService.loadUserByUserEmail(principal.getName()));
        System.out.println(userService.loadUserByUsername(principal.getName()).getUsername());
        return "admin";
    }


    @PostMapping("/new-user")
    public String createNewUser(@ModelAttribute("newUser") User newUser, @ModelAttribute("role") String role) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userService.saveUser(newUser, role);
        return "redirect:/admin/";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @ModelAttribute("role") String role) {
        userService.updateUser(user, role);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@ModelAttribute("user") User deletedUser, @PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin/";
    }
}
