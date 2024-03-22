package org.example.reservation.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CheckoutRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 予約画面コントローラー
 */
@Controller
@RequiredArgsConstructor
public class ReservationController {

	private final ShoppingCartService shoppingCartService;

	/**
	 * GET:注文者情報入力画面
	 * @param model reservationForm
	 * @return /reservation
	 */
	@GetMapping("/takeout/product/reservation")
	public String showReservationForm(Model model, HttpSession session) {
		model.addAttribute("reservationForm", new ReservationInputForm());
		model.addAttribute("session", session.getAttribute("request"));
		return "reservation";
	}

	/**
	 * POST:予約情報の確定
	 * @param form ReservationInputForm
	 * @return html:/confirmation
	 */
	@PostMapping("/takeout/product/completeOrder")
	public String completeOrderView(@Validated @ModelAttribute("inputReservationForm")
									ReservationInputForm form, BindingResult bindingResult, @SessionAttribute("request") CheckoutRequest request) {
		if(!bindingResult.hasErrors()) {
			//予約をデータベースに登録する。
			shoppingCartService.finalizeCheckout(request, form);
			return "confirmation";
		}
		return "redirect:/takeout/product/reservation";
	}

	//カレンダーから日時指定をする
	@PostMapping("/takeout/product/submitDate")
	public String submitDate(@RequestParam("datetime") String datetime, Model model)
	{
		// 文字列をLocalDateTimeに変換（フォーマットはフロントエンドで選択した日時に応じて）
		LocalDateTime parsedDateTime = LocalDateTime.parse(datetime);
		model.addAttribute("selectedDateTime", parsedDateTime.toString());
		return "resultPage";
	}


	@PostMapping("/reservation/submit")
	public String submitReservation(@ModelAttribute ReservationInputForm form, HttpSession session) {
		// 予約者情報をセッションに保存
		session.setAttribute("reservation", form);
		// 決済画面にリダイレクト
		return "redirect:/checkout";
	}

}

