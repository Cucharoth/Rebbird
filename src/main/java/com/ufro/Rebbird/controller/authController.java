package com.ufro.Rebbird.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.model.utils.Role;
import com.ufro.Rebbird.service.UserService;

@Controller
public class authController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String logIn(Model model) {
        return "log-in";
    }

    @GetMapping("/login/from-create-post")
    public String fromCreatePost(Model model) {
        model.addAttribute("fromCreatePost", true);
        return "log-in";
    }

    @RequestMapping("/register")
    public String register(Model model, Principal p) {
        model.addAttribute("user", new User());

        if (p != null) {
            // Logged user info
            // int userId = userService.findByUserName(p.getName()).getId();
            // model.addAttribute("userId", userId);
        }

        return "sign-in";
    }

    @PostMapping("/new-user")
    public String newUser(@ModelAttribute User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userService.save(user);
        return "redirect:/login";
    }
}
