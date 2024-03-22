package org.example.reservation.controller;

import java.time.LocalDateTime;
import jakarta.servlet.http.HttpSession;
import org.example.reservation.entity.Reservation;
import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.service.implement.ShoppingCartServiceImpl;
import org.example.reservation.service.spec.OrderService;
import org.example.reservation.service.spec.ReservationService;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CheckoutRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

/**
 * 予約画面コントローラー
 */
@Controller
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;
	private final ShoppingCartService shoppingCartService;
	private final OrderService orderService;

	/**
	 * GET:注文者情報入力画面
	 * @param model reservationForm
	 * @return /reservation
	 */
	@GetMapping("/takeout/product/reservation")
	public String showReservationForm(Model model, HttpSession session) {
		model.addAttribute("reservationForm", new ReservationInputForm());
		model.addAttribute("session", session.getAttribute("request"));
		return "/reservation";
	}

//	/**
//	 * POST:注文確定画面の表示
//	 * @param form field:id/name/phone/email/date
//	 * @param bindingResult reservationFrom error
//	 * @return true:/confirmation  false:/redirect:"reservationFrom"
//	 */
//	@PostMapping("/takeout/product/confirm")
//	public String showConfirm(@Validated @ModelAttribute("inputReservationForm")
//							  ReservationInputForm form, BindingResult bindingResult,HttpSession session) {
//		//FormValidation check
//		if(bindingResult.hasErrors()) {
//			return "redirect:/takeout/product/reservation";}
//		//予約をデータベースに登録する。
//		CheckoutRequest request = (CheckoutRequest) session.getAttribute("request");
//		shoppingCartService.finalizeCheckout(request, form);
//		return "/confirmation";
//	}

	//カレンダーから日時指定をする
	@PostMapping("/takeout/product/submitDate")
	public String submitDate(@RequestParam("datetime") String datetime, Model model)
	{
		// 文字列をLocalDateTimeに変換（フォーマットはフロントエンドで選択した日時に応じて）
		LocalDateTime parsedDateTime = LocalDateTime.parse(datetime);
		model.addAttribute("selectedDateTime", parsedDateTime.toString());
		return "resultPage";
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
			return "/confirmation";
		}
		return "redirect:/takeout/product/reservation";


	}

	@PostMapping("/reservation/submit")
	public String submitReservation(@ModelAttribute ReservationInputForm form, HttpSession session) {
		// 予約者情報をセッションに保存
		session.setAttribute("reservation", form);
		// 決済画面にリダイレクト
		return "redirect:/checkout";
	}

}

