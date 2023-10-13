package com.ufro.Rebbird.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufro.Rebbird.model.Post;
import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.service.PostService;
import com.ufro.Rebbird.service.UserService;

@Controller
@RequestMapping(path = "/index")
public class IndexController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(@RequestParam(value = "id") int categoryId, Model model) {
        Iterable<Post> posts = postService.findAllByCategoryIdOrderByDateDesc(categoryId);

        // User user = userService.getUser(0);

        model.addAttribute("posts", posts);
        return "index-login";
    }

    /*
     * @GetMapping("test")
     * public List<Post> getById() {
     * return postService.getPosts();
     * }
     */
}