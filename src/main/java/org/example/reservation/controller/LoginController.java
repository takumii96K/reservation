package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.form.UserRegistrationForm;
import org.example.reservation.service.spec.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;


    @GetMapping("/login")
    public String loginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("failureMessage", "IDもしくはパスワードが違います");
        }
        return "/user/login";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("inputRegister") UserRegistrationForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "エラー");
            return "/user/register";
        }
        service.registerUser(form);
        return "redirect:/user/login";
    }

    /**
     * 登録画面のGET
     * @return 登録画面 "register.html"
     */
    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute("inputRegisterForm", new UserRegistrationForm());
        return "/user/register";
    }

}
