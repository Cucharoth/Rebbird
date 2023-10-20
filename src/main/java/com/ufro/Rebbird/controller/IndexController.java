package com.ufro.Rebbird.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufro.Rebbird.model.Category;
import com.ufro.Rebbird.model.Post;
import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.model.UserPostReaction;
import com.ufro.Rebbird.model.utils.ReactionType;
import com.ufro.Rebbird.service.CategoryService;
import com.ufro.Rebbird.service.PostService;
import com.ufro.Rebbird.service.UserPostReactionService;
import com.ufro.Rebbird.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping
@AllArgsConstructor
public class IndexController {

    private final PostService postService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final UserPostReactionService userPostReactionService;

    /**
     * Maneja pagina principal, muestra posts según categorías dando acceso al
     * perfil
     * de usuario y a la creación de publicaciones.
     * 
     * @param categoryId id de la categoría seleccionada, necesaria para realizar el
     *                   filtro correspondiente.
     * @param model      modelo proporcionado por Spring.
     * @param principal  principal proporcionado por Spring Security, representa el
     *                   usuario autorizado.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @GetMapping(path = "/index")
    public String index(@RequestParam(value = "id") int categoryId, Model model, Principal principal) {

        Iterable<Post> postsResult = postService.findAllByCategoryIdOrderByDateDesc(categoryId);
        Category category = categoryService.findCategoryByid(categoryId);
        // posts.iterator().next()
        if (category != null) {
            String categoryName = category.getName();
            model.addAttribute("isPostsEmpty", !postsResult.iterator().hasNext());
            model.addAttribute("categoryName", categoryName);
            model.addAttribute("postsEmptyCheck", postsResult);

            // checks if the user is logged in
            if (principal != null) {
                String userName = principal.getName();
                User user = userService.findByUserName(userName);
                model.addAttribute("userId", user.getId());
                model.addAttribute("userName", user.getName());
                model.addAttribute("userProfileImg", user.getProfileImg().getLink());
                model.addAttribute("userLogin", true);
                model.addAttribute("posts", addUserReaction(user, postsResult));
            } else {
                model.addAttribute("userLogin", false);
                model.addAttribute("posts", addUserReaction(null, postsResult));
            }
            return "index";
        } else {
            // TODO: 404.
            return "index";
        }
    }

    
    private List<List<Object>> addUserReaction(User user, Iterable<Post> currentPosts) {
        List<List<Object>> posts = new ArrayList<List<Object>>();
        if (user != null) {
            for (Post post : currentPosts) {
                List<Object> postInfo = new ArrayList<Object>();
                boolean hasReacted = false, isLike = false;
                UserPostReaction userPostReaction = userPostReactionService.findByPostAndUser(post.getId(),
                        user.getId());
                if (userPostReaction != null) {
                    hasReacted = true;
                    isLike = userPostReaction.getReactionType().equals(ReactionType.LIKE);
                }
                postInfo.add(post);
                postInfo.add(hasReacted);
                postInfo.add(isLike);
                posts.add(postInfo);
            }
        } else {
            // se hace de esta forma para mantener formato en vista 'index'.
            for (Post post : currentPosts) {
                List<Object> postInfo = new ArrayList<Object>();
                postInfo.add(post);
                posts.add(postInfo);
            }
        }
        return posts;
    }

    /**
     * Maneja búsqueda de publicación según titulo.
     * 
     * @param keyword   palabra contenida en el titulo a buscar.
     * @param model     modelo proporcionado por Spring.
     * @param principal principal proporcionado por Spring Security, representa el
     *                  usuario autorizado.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @GetMapping(path = "/search")
    public String search(@RequestParam("keyword") String keyword, Model model, Principal principal) {
        Iterable<Post> postsResult = postService.findAllByTitleContainingIgnoreCaseOrderByDateDesc(keyword);

        if (postsResult != null) {
            model.addAttribute("categoryName", "Búsqueda");
            model.addAttribute("postsEmptyCheck", postsResult);
            if (principal != null) {
                String userName = principal.getName();
                User user = userService.findByUserName(userName);
                model.addAttribute("userId", user.getId());
                model.addAttribute("userName", user.getName());
                model.addAttribute("userProfileImg", user.getProfileImg().getLink());
                model.addAttribute("userLogin", true);
                model.addAttribute("posts", addUserReaction(user, postsResult));
            } else {
                model.addAttribute("posts", addUserReaction(null, postsResult));
            }
            return "index";
        } else {
            // TODO: 404.
            return "index";
        }
    }

    /**
     * Maneja Reacciones entre usuario y publicación.
     * 
     * @param reactionType tipo de reacción.
     * @param postId       id de la publicación con la que se ha interactuado
     * @param categoryId   categoría de la publicación con que se ha interactuado
     * @param principal    principal proporcionado por Spring Security, representa
     *                     el
     *                     usuario autorizado.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @PostMapping(path = "/reaction")
    public String likePost(
            @RequestParam("type") String reactionType,
            @RequestParam("post-id") Long postId,
            @RequestParam("category-id") int categoryId,
            Principal principal) {
        Post post = postService.findById(postId);
        if (post != null) {
            User user = userService.findByUserName(principal.getName());
            manageReaction(reactionType, post, user);
            return "redirect:/index?id=" + categoryId;
        } else {
            // TODO: 404.
            return "redirect:/index?id=" + categoryId;
        }
    }

    @GetMapping(path = "/")
    public String index() {
        return "redirect:/index?id=1";
    }

    private void manageReaction(String reactionType, Post post, User user) {
        UserPostReaction userPostReaction = userPostReactionService.findByPostAndUser(post.getId(), user.getId());
        int reactionAmount = post.getReactionAmount();

        // extra check para que un usuario no pueda dar like repetidas veces.
        if (userPostReaction == null) {
            userPostReaction = new UserPostReaction();
            userPostReaction.setUser(user);
            userPostReaction.setPost(post);
            if (reactionType.equals("like")) {
                // update values
                userPostReaction.setReactionType(ReactionType.LIKE);
                post.setReactionAmount(reactionAmount + 1);
            } else {
                userPostReaction.setReactionType(ReactionType.DISLIKE);
                post.setReactionAmount(reactionAmount - 1);
            }
            // save
            userPostReactionService.saveOrUpdate(userPostReaction);
            postService.save(post);

        } else {
            switch (userPostReaction.getReactionType()) {
                case DISLIKE:
                    if (reactionType.equals("like")) {
                        post.setReactionAmount(reactionAmount + 2);
                        userPostReaction.setReactionType(ReactionType.LIKE);
                        userPostReactionService.saveOrUpdate(userPostReaction);
                    } else {
                        post.setReactionAmount(reactionAmount + 1);
                        userPostReactionService.delete(userPostReaction);
                    }
                    break;
                case LIKE:
                    if (reactionType.equals("like")) {
                        post.setReactionAmount(reactionAmount - 1);
                        userPostReactionService.delete(userPostReaction);
                    } else {
                        post.setReactionAmount(reactionAmount - 2);
                        userPostReaction.setReactionType(ReactionType.DISLIKE);
                        userPostReactionService.saveOrUpdate(userPostReaction);
                    }
                    break;
            }
            postService.save(post);
        }

    }
}