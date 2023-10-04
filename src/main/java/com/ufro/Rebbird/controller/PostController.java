package com.ufro.Rebbird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.service.PostService;
import com.ufro.Rebbird.service.UserService;

@Controller
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String profile(@RequestParam(value = "id") int id, Model model) {
        // User user = userService.getUser(id).orElse(null);

        // model.addAttribute("user", user.getNombre());
        return "index-login";
    }
}
