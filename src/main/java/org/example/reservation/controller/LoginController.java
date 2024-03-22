package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.exception.DuplicateUserNameException;
import org.example.reservation.form.UserRegistrationForm;
import org.example.reservation.service.spec.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	 * @param model Modelオブジェクト
	 * @param error エラーメッセージ
	 * @param failure エラーメッセージ
	 * @param complete 完了メッセージ
	 * @return view: /user/login.html
	 */
	@GetMapping("/login")
	public String loginForm(Model model, @RequestParam(value = "failure", required = false) String failure,
			@RequestParam(value = "complete", required = false) String complete) {
		if (failure != null) {
			model.addAttribute("failureMessage", "※IDもしくはパスワードが違います");
		}
		if (complete != null) {
			model.addAttribute("completeMsg", "登録が完了しました！");
		}
		return "user/login";
	}

	//    public String loginForm(@RequestParam(value = "failure", required = false) Model model) {
	//        return "/user/login";
	//    }

	/**
	 * GET:ユーザー登録画面遷移
	 * @param model inputRegisterForm
	 * @return view: /user/register.html
	 */
	@GetMapping("/register")
	public String registerUser(Model model){
		model.addAttribute("inputRegisterForm", new UserRegistrationForm());
		return "user/register";
	}

	/**
	 * POST:ユーザー登録
	 * @param form UserRegistrationForm
	 * @param result BindingResult
	 * @return view:/user/register.html redirect:/user/login
	 */
	@PostMapping("/register")
	public String submitForm(@ModelAttribute("inputRegister") UserRegistrationForm form, BindingResult result, 
			RedirectAttributes redirectAttributes) {

		try {
			// ユーザーの登録処理
			service.registerUser(form);
			return "redirect:/login?complete=true";
		} catch (DuplicateUserNameException e) {
			redirectAttributes.addFlashAttribute("error", "※IDが重複しています");
			return "redirect:/register";
		}
	}
	//    @PostMapping("/register")
	//    public String submitForm(@Validated @ModelAttribute("inputRegister") UserRegistrationForm form, BindingResult result,
	//                             RedirectAttributes redirectAttributes) {
	//        if (result.hasErrors()) {
	//        	//return "redirect:register";
	//            return "redirect:/user/register";
	//        }
	//        service.registerUser(form);
	//        redirectAttributes.addFlashAttribute("completeMsg", "登録が完了しました。");
	//        return "redirect:/login?complete=true";
	//    }

}
