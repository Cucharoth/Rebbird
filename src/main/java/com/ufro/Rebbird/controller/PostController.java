package com.ufro.Rebbird.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufro.Rebbird.model.Post;
import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.service.PostService;
import com.ufro.Rebbird.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public String profile(@RequestParam(value = "id") Long id, Model model, Principal principal) {
        Post post = postService.findById(id);

        if (post != null) {
            model.addAttribute("post", post);
            model.addAttribute("categoryName", post.getCategory().getName());
            if (principal != null) {
                String userName = principal.getName();
                User user = userService.findByUserName(userName);
                model.addAttribute("userName", user.getName());
                model.addAttribute("userProfileImg", user.getProfileImg().getLink());
                model.addAttribute("userLogin", true);
            }
            return "on-post-login";
        } else {
            // TODO: 404
            return "on-post-login";
        }
    }
}
