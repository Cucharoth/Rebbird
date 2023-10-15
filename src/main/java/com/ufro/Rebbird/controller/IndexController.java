package com.ufro.Rebbird.controller;

import java.security.Principal;
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

import com.ufro.Rebbird.model.Category;
import com.ufro.Rebbird.model.Post;
import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.service.CategoryService;
import com.ufro.Rebbird.service.PostService;
import com.ufro.Rebbird.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "/index")
@AllArgsConstructor
public class IndexController {

    private final PostService postService;
    private final UserService userService;
    private final CategoryService categoryService;

    @GetMapping
    public String index(@RequestParam(value = "id") int categoryId, Model model, Principal principal) {

        // TODO: REMOVE THIS SOUT BEFORE RELEASE
        System.out.println(principal);

        Iterable<Post> posts = postService.findAllByCategoryIdOrderByDateDesc(categoryId);
        Category category = categoryService.findCategoryByid(categoryId);
        if (category != null) {
            String categoryName = category.getName();
            model.addAttribute("categoryName", categoryName);
            model.addAttribute("posts", posts);

            // checks if the user is logged in
            if (principal != null) {
                String userName = principal.getName();
                User user = userService.findByUserName(userName);
                model.addAttribute("userId", user.getId());
                model.addAttribute("userName", user.getName());
                model.addAttribute("userProfileImg", user.getProfileImg().getLink());
                model.addAttribute("userLogin", true);
            } else {
                model.addAttribute("userLogin", false);
            }

            return "index";
        } else {
            // TODO: TENTATIVO, AÑADIR UNA PAGINA 404.
            return "index";
        }
    }
}