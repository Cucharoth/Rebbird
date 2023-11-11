package com.ufro.Rebbird.controller;

import java.security.Principal;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;


import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String logIn(Model model, HttpSession session) {
        if (session.getAttribute("message") != null) {
            model.addAttribute("message", session.getAttribute("message").toString());
            session.removeAttribute("message");
        }
        return "log-in";
    }

    @GetMapping("/auth-failure")
    public String authFailureHandler(HttpSession session) {
        session.setAttribute("message", "¡Credenciales incorrectas!");
        return "redirect:/login";
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
            int userId = userService.findByUserName(p.getName()).getId();
            model.addAttribute("userId", userId);
        }

        return "register";
    }

    @GetMapping("/password-reset")
    public String showPasswordResetForm(Model model) {
        model.addAttribute("user", new User());
        return "password-reset";
    }

    @PostMapping("/password-reset")
    public String handlePasswordReset(@ModelAttribute User user, Model model, HttpSession session) {
        User existingUser = userService.findByUserName(user.getUsername());
        if (existingUser != null) {
            String token = generatePasswordResetToken();
            session.setAttribute("resetToken", token);
            session.setAttribute("user", existingUser);
            return "redirect:/new-password";
        } else {
            model.addAttribute("error", "¡El usuario no ha sido encontrado!");
            return "password-reset";
        }
    }

    private String generatePasswordResetToken() {
        return UUID.randomUUID().toString();
    }

    @GetMapping("/new-password")
    public String showNewPasswordForm(Model model) {
        model.addAttribute("user", new User());
        return "new-password";
    }

    @PostMapping("/new-password")
    public String handleNewPassword(@ModelAttribute User userFromModel, Model model,
            HttpSession session) {
        String resetToken = (String) session.getAttribute("resetToken");
        User existingUser = (User) session.getAttribute("user");
        if (resetToken != null && existingUser != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            existingUser.setPassword(encoder.encode(userFromModel.getPassword()));
            userService.saveOrUpdate(existingUser);
            session.removeAttribute("resetToken");
            session.removeAttribute("user");
            session.setAttribute("message", "¡Contraseña reiniciada exitosamente!");
            return "redirect:/login";
        } else {
            model.addAttribute("error", "El token de restablecimiento de contraseña no es válido");
            return "redirect:/password-reset";
        }
    }

    @PostMapping("/confirm-password")
    public String confirmPassword(@ModelAttribute User user, @RequestParam String confirmPassword, Model model,
            RedirectAttributes redirectAttributes) {
        if (user.getPassword().equals(confirmPassword)) {
            return "redirect:/new-user";
        } else {
            redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/register";
        }
    }

    @PostMapping("/new-user")
    public String newUser(
            @ModelAttribute User user,
            @RequestParam String confirmPassword,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (user.getPassword().equals(confirmPassword)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            ProfileImg profileImg = profileImgService.findById(1);
            user.setNombre(normaliseString(user.getName()));
            user.setPassword(encoder.encode(normaliseString(user.getPassword())));
            user.setRole(Role.USER);
            if (profileImg != null) {
                user.setProfileImg(profileImg);
                userService.save(user);
            } else {
                return "error";
            }
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/register";
        }
    }

    private String normaliseString(String name) {
        return name.replaceAll("\\s", "");
    }
}
