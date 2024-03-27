package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.config.UserDetailsIml;
import org.example.reservation.exception.DuplicateUserNameException;
import org.example.reservation.form.UserRegistrationForm;
import org.example.reservation.service.spec.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService service;

	/**
	 * GET:ログイン画面表示
	 * @param model errorメッセ
	 * @param params requestParam="message"
	 * @return "/login"
	 */
	@GetMapping("/login")
	public String loginForm(Model model,
                            @RequestParam Map<String, String> params, Authentication authentication) { // すべてのリクエストパラメータをマップとして受け取る

		// ログイン失敗メッセージ
		if (params.containsKey("failure")) {
			model.addAttribute("failureMessage", "※IDもしくはパスワードが違います");
		}

		// 登録完了メッセージ
		if (params.containsKey("complete")) {
			model.addAttribute("completeMsg", "登録が完了しました！");
		}

		// エラーメッセージ
		String errorType = params.get("error");
		if (errorType != null) {
			switch (errorType) {
				case "accessDenied":
					model.addAttribute("errorMessage", "アクセス権限がありません。");
					break;
				case "unauthorized":
					model.addAttribute("errorMessage", "ログインが必要です。");
					break;
				// 他のエラータイプがある場合はここに追加
			}
		}

        if (authentication != null && authentication.isAuthenticated()) {
            // ユーザーが既にログインしている場合は、ホームページにリダイレクト
            return "redirect:/takeout/top";
        }
		return "user/login";
	}

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
	@GetMapping("/user/profile")
	public String myPage(@AuthenticationPrincipal UserDetailsIml userDetails, Model model) {
		model.addAttribute("user", userDetails.getUser());
		// 例: 注文履歴、お気に入り商品など
		return "mypage";
	}

}
