package com.ufro.Rebbird.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufro.Rebbird.model.Comment;
import com.ufro.Rebbird.model.Post;
import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.model.UserPostReaction;
import com.ufro.Rebbird.service.CommentService;
import com.ufro.Rebbird.service.PostService;
import com.ufro.Rebbird.service.UserPostReactionService;
import com.ufro.Rebbird.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final UserPostReactionService userPostReactionService;
    private final CommentService commentService;

    @GetMapping
    public String onPost(@RequestParam(value = "id") Long postId, Model model, Principal principal) {
        Post post = postService.findById(postId);
        Iterable<Comment> comments = commentService.findAllByPostIdOrderByDate(postId);

        if (post != null) {
            model.addAttribute("post", post);
            model.addAttribute("categoryName", post.getCategory().getName());
            model.addAttribute("comments", comments);
            model.addAttribute("newComment", new Comment());
            if (principal != null) {
                String userName = principal.getName();
                User user = userService.findByUserName(userName);
                model.addAttribute("userName", user.getName());
                model.addAttribute("userProfileImg", user.getProfileImg().getLink());
                model.addAttribute("userLogin", true);
            }
            return "on-post";
        } else {
            return "error";
        }
    }

    @PostMapping(path = "/delete")
    public String deletePost(@RequestParam(value = "postId") Long postId,
            @RequestParam(value = "categoryId") int categoryId,
            Principal principal) {
        User user = userService.findByUserName(principal.getName());
        Post post = postService.findById(postId);
        if (post != null) {
            UserPostReaction userPostReaction = userPostReactionService.findByPostAndUser(post.getId(), user.getId());
            if (userPostReaction != null)
                userPostReactionService.delete(userPostReaction);
            postService.delete(post);
        }

        return "redirect:/index?id=" + categoryId;
    }

    @PostMapping(path = "/comment/save")
    public String saveComment(
            @RequestParam(value = "postId") Long postId,
            @ModelAttribute Comment comment,
            Principal principal) {
        User user = userService.findByUserName(principal.getName());
        Post post = postService.findById(postId);
        if (post != null && user != null) {
            comment.setPost(post);
            comment.setAuthor(user);
            commentService.save(comment);
        } else {
            return "error";
        }
        return "redirect:/post?id=" + postId;
    }
}
