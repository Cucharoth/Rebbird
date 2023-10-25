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

import com.ufro.Rebbird.model.ProfileImg;
import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.model.utils.Role;
import com.ufro.Rebbird.service.ProfileImgService;
import com.ufro.Rebbird.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class authController {

    private final UserService userService;
    private final ProfileImgService profileImgService;

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

        return "register";
    }

    @PostMapping("/new-user")
    public String newUser(@ModelAttribute User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ProfileImg profileImg = profileImgService.findById(1);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        if (profileImg != null)
            user.setProfileImg(profileImg);
        userService.save(user);
        return "redirect:/login";
    }
}
