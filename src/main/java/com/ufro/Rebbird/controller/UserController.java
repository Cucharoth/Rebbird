package com.ufro.Rebbird.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(@RequestParam(value = "id") int id, Model model) {
        User user = userService.getUser(id).orElse(null);

        model.addAttribute("user", user.getName());
        return "index-login";
    }

    @GetMapping("/{userId}")
    public Optional<User> getById(@PathVariable("userId") int userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public void saveUpdate(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }

    @DeleteMapping("{userId}")
    public void saveUpdate(@PathVariable("userId") int userId) {
        userService.delete(userId);
    }
}
