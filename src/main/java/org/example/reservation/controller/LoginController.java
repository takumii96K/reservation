package org.example.reservation.controller;


import lombok.RequiredArgsConstructor;
import org.example.reservation.form.UserRegistrationForm;
import org.example.reservation.service.spec.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;

    /**
     * GET:ログイン画面遷移
     * @param model failureMessage

     * @return view: /user/login.html
     */
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "failure", required = false) Model model) {
        return "/user/login";
    }

    /**
     * GET:ユーザー登録画面遷移
     * @param model inputRegisterForm
     * @return view: /user/register.html
     */
    @GetMapping("/register")
    public String registerUser(Model model){
        model.addAttribute("inputRegisterForm", new UserRegistrationForm());
        return "/user/register";
    }

    /**
     * POST:ユーザー登録
     * @param form UserRegistrationForm
     * @param result BindingResult
     * @return view:/user/register.html redirect:/user/login
     */
    @PostMapping("/register")
    public String submitForm(@Validated @ModelAttribute("inputRegister") UserRegistrationForm form, BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {

            return "redirect:/user/register";
        }
        service.registerUser(form);
        redirectAttributes.addFlashAttribute("registrationComplete", "登録が完了しました。");
        return "redirect:/user/login";
    }

}
