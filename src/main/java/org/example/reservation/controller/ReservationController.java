package org.example.reservation.controller;

import java.time.LocalDateTime;

import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.service.spec.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

//予約内容確認画面コントローラ
@Controller
@RequiredArgsConstructor
public class ReservationController {
	/**
	 * ReservationService
	 */
	private final ReservationService reservationService;

	/**
	 * GET:注文者情報入力画面
	 * @param model reservationForm
	 * @return /reservation
	 */
	@GetMapping("/takeout/product/reservation")
	public String showReservationForm(Model model) {
		model.addAttribute("reservationForm", new ReservationInputForm());
		return "/reservation";
	}

	/**
	 * POST:注文確定画面の表示
	 * @param form field:id/name/phone/email/date
	 * @param bindingResult reservationFrom error
	 * @return true:/confirmation  false:/redirect:"reservationFrom"
	 */
	@PostMapping("/takeout/product/confirm")
	public String showConfirm(@Validated @ModelAttribute("inputReservationForm")
							  ReservationInputForm form, BindingResult bindingResult) {


		//FormValidation check
		if(bindingResult.hasErrors()) {
			return "redirect:/takeout/product/reservation";}
		//予約をデータベースに登録する。
		boolean save = reservationService.registerReservation(form);

		return "/confirmation";
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

	/**
	 * POST:予約情報の確定
	 * @param form ReservationInputForm
	 * @return html:/confirmation
	 */
	@PostMapping("/takeout/product/completeOrder")
	public String completeOrderView(ReservationInputForm form) {
		reservationService.registerReservation(form);

		return "/confirmation";
	}

}

