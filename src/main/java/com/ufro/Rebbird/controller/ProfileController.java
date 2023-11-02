package com.ufro.Rebbird.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "/profile")
@AllArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String historial(@RequestParam(value = "id") int userId, Model model, Principal principal) {
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);
        }

        return "panel-usuario-historial";
    }

    @GetMapping(path = "/conf")
    public String configuracion(@RequestParam(value = "id") int userId, Model model, Principal principal) {
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);
        }
        return "panel-usuario-conf";
    }
    @GetMapping(path = "/edit-perfil")
    public String editPerfil(@RequestParam(value = "id") int userId, Model model, Principal principal) {
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);
        }
        return "panel-usuario-edit-perfil";
    }
    @GetMapping(path = "/edit-avatar")
    public String editAvatar(@RequestParam(value = "id") int userId, Model model, Principal principal) {
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);
        }
        return "panel-usuario-edit-avatar";
    }

}
