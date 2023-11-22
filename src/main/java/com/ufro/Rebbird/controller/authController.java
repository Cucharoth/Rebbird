package com.ufro.Rebbird.controller;

import jakarta.servlet.http.HttpSession;
import java.util.UUID;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufro.Rebbird.model.Category;
import com.ufro.Rebbird.model.ProfileImg;
import com.ufro.Rebbird.model.User;
import com.ufro.Rebbird.model.utils.Role;
import com.ufro.Rebbird.service.CategoryService;
import com.ufro.Rebbird.service.ProfileImgService;
import com.ufro.Rebbird.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class authController {

    private final UserService userService;
    private final ProfileImgService profileImgService;
    private final CategoryService categoryService;

    /**
     * Maneja el <i>Login</i> del usuario
     * 
     * @param model   modelo proporcionado por Spring.
     * @param session session proporcionada por Spring.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @RequestMapping("/login")
    public String logIn(Model model, HttpSession session) {
        return "log-in";
    }

    /**
     * Maneja la verificación de credenciales fallida.
     * 
     * @param redirectAttributes proporcionado por Spring, nos permite enviar
     *                           atributos a otras direcciones, util para redirigir.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @GetMapping("/auth-failure")
    public String authFailureHandler(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "¡Credenciales incorrectas!");
        return "redirect:/login";
    }

    /**
     * Maneja redirección desde el botón 'crear publicación' en index.
     * 
     * @param model modelo proporcionado por Spring.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @GetMapping("/login/from-create-post")
    public String fromCreatePost(Model model) {
        model.addAttribute("fromCreatePost", true);
        return "log-in";
    }

    /**
     * Maneja registro de usuarios.
     * 
     * @param model modelo proporcionado por Spring.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // remueve todos los atributos de una session.
    // private void removeAllSessionAtrr(HttpSession session) {
    // session.getAttributeNames().asIterator().forEachRemaining(name ->
    // session.removeAttribute(name));
    // }

    /**
     * Muestra vista reinicio de contraseñas.
     * 
     * @param model modelo proporcionado por Spring.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @GetMapping("/password-reset")
    public String showPasswordResetForm(Model model) {
        model.addAttribute("user", new User());
        return "password-reset";
    }

    /**
     * Maneja la búsqueda del usuario que desea reiniciar contraseña..
     * 
     * @param user    <i>User</i> que posee el nombre de usuario a ser procesado.
     * @param model   modelo proporcionado por Spring.
     * @param session sesión, utilizado para mantener parámetros por periodos
     *                extensos.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
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

    /**
     * Muestra y prepara la vista para reiniciar contraseña.
     * 
     * @param model modelo proporcionado por Spring.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @GetMapping("/new-password")
    public String showNewPasswordForm(Model model) {
        model.addAttribute("user", new User());
        return "new-password";
    }

    /**
     * Maneja el reinicio de contraseña.
     * 
     * @param userFromModel      <i>User</i> que posee la nueva contraseña a ser
     *                           guardada.
     * @param model              modelo proporcionado por Spring.
     * @param session            sesión, utilizado para mantener parámetros por
     *                           periodos extensos.
     * @param redirectAttributes proporcionado por Spring, nos permite enviar
     *                           atributos a otras direcciones, util para redirigir.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @PostMapping("/new-password")
    public String handleNewPassword(@ModelAttribute User userFromModel, Model model,
            HttpSession session, RedirectAttributes redirectAttributes) {
        String resetToken = (String) session.getAttribute("resetToken");
        User existingUser = (User) session.getAttribute("user");
        if (resetToken != null && existingUser != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            existingUser.setPassword(encoder.encode(userFromModel.getPassword()));
            userService.saveOrUpdate(existingUser);
            session.removeAttribute("resetToken");
            session.removeAttribute("user");
            redirectAttributes.addFlashAttribute("message", "¡Contraseña reiniciada exitosamente!");
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

    /**
     * Maneja la creación de nuevos usuarios.
     * 
     * @param user               <i>User</i> con los datos del usuario a ser
     *                           registrado.
     * @param confirmPassword    contraseña ingresada por el usuario, utilizada para
     *                           evitar errores de ingreso.
     * @param model              modelo proporcionado por Spring.
     * @param redirectAttributes proporcionado por Spring, nos permite enviar
     *                           atributos a otras direcciones, util para redirigir.
     * @return <i>View</i> manejado por Thymeleaf.
     * 
     */
    @PostMapping("/new-user")
    public String newUser(
            @ModelAttribute User user,
            @RequestParam String confirmPassword,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (userExist(user.getUsername())) {
            redirectAttributes.addFlashAttribute("message", "¡El nombre de usuario no esta disponible!");
            return "redirect:/register";
        }

        if (user.getPassword().equals(confirmPassword)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            ProfileImg profileImg = profileImgService.findById(1);
            Category favCategory = categoryService.findCategoryByid(1);
            user.setNombre(normaliseString(user.getName()));
            user.setPassword(encoder.encode(normaliseString(user.getPassword())));
            user.setRole(Role.USER);

            if (profileImg != null && favCategory != null) {
                user.setFavCategory(favCategory);
                user.setProfileImg(profileImg);
                user.setDescripcion("");
                userService.save(user);
                redirectAttributes.addFlashAttribute("message", "¡Cuenta creada exitosamente!");
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

    private boolean userExist(String username) {
        return userService.findByUserName(username) != null;
    }
}
