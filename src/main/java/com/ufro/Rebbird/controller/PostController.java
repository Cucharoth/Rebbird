package com.ufro.Rebbird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufro.Rebbird.model.Post;
import com.ufro.Rebbird.service.PostService;

@Controller
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String profile(@RequestParam(value = "id") Long id, Model model) {
        Post post = postService.findById(id);

        model.addAttribute("post", post);
        return "on-post-login";
    }
}
