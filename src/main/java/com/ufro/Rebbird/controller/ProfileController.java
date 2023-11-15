package com.ufro.Rebbird.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(path = "/profile")
@AllArgsConstructor
public class ProfileController {

    private final UserService userService;

    // todo: cucha: wtf porque pedi id, nisiquiera lo uso, reevaluar uso de id
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

    // todo: cucha: esto no se esta usando, verdad?
    @GetMapping(path = "/edit-avatar")
    public String editAvatar(@RequestParam(value = "id") int userId, Model model, Principal principal) {
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);

        }
        return "panel-usuario-edit-avatar";
    }

    @GetMapping(path = "/eliminar-cuenta")
    public String eliminarCuenta(@RequestParam(value = "id") int userId, Model model, Principal principal) {
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);
        }
        return "panel-usuario-eliminar-cuenta";
    }

    @PostMapping(path = "/deleteAccount")
    public String deleteAccount(@RequestParam(value = "id") int userId, Model model, Principal principal) {
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);

            userService.delete(userId);
        }
        return "redirect:/logout";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam(value = "id") int userId, Model model, Principal principal,
                                @RequestParam(value = "descripcion") String descripcion,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "favCategory") int favCategory){
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);

            if (descripcion != "") {
                userService.changeDescripcion(userId, descripcion);
            }
            if (username != "") {
                userService.changeUsername(userId, username);
                return "redirect:/logout";
            }
            if (favCategory > 0 && favCategory <= 9) {
                userService.changeFavCategory(userId, favCategory);
            }
        }
        return "redirect:/profile?id=" + userId;
    }





    // todo: cucha: el id del usuario no se esta usando 
    @PostMapping(path = "/updateAvatar")
    public String updateAvatar(@RequestParam(value = "id") int userId, Model model, Principal principal,
                               @RequestParam(value = "profileImg") int profileImg) {
        if (principal != null) {
            String userName = principal.getName();
            User user = userService.findByUserName(userName);
            model.addAttribute("user", user);

            if(profileImg > 0) {
                userService.changeImgPerfil(userId, profileImg);
            }
        }
        return "redirect:/profile?id=" + userId;
    }
}



