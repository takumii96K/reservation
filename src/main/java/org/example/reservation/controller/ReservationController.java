package org.example.reservation.controller;

import java.time.LocalDateTime;

import org.example.reservation.form.ReservationForm;
import org.example.reservation.service.spec.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//予約内容確認画面コントローラ
@Controller
@RequestMapping("/takeout/product")
@RequiredArgsConstructor
public class ReservationController
{
	// DI対象
	//
	//	private final ProductService service;
	private final ReservationService reservationService;

	//注文内容を確認する
	@GetMapping("/reservation")
	public String showReservationForm(Model model)
	{
		model.addAttribute("reservationForm", new ReservationForm());
		return "/reservation";
	}

	@PostMapping("/confirm")
	public String showConfirm(@Valid @ModelAttribute("inputReservationForm") ReservationForm form,BindingResult result,Model model)
	{
		// ここでフォームのデータを使用した処理を実行します。
		// 例えば、予約データをデータベースに保存するなど。

		//エラーがあった場合予約画面にリダイレクト
		if(result.hasErrors())
		{
			return "redirect:/takeout/product/reservation";
		}
		else
		{
			//setReservationRegisterの戻り値がtrueだった場合
			if(reservationService.setReservationRegister(form))
			{
				return "/confirmation";//結果表示ページを戻り値として返す
			}
		}

		return "/confirmation";
	}

	//カレンダーから日時指定をする
	@PostMapping("/submitDate")
	public String submitDate(@RequestParam("datetime") String datetime, Model model)
	{
		// 文字列をLocalDateTimeに変換（フォーマットはフロントエンドで選択した日時に応じて）
		LocalDateTime parsedDateTime = LocalDateTime.parse(datetime);
		model.addAttribute("selectedDateTime", parsedDateTime.toString());
		return "resultPage";
	}

	//確定画面表示
	@PostMapping("/completeOrder")
	public String completeOrderView(ReservationForm form)
	{

		reservationService.setReservationRegister(form);

		return "confirmation";

	}

}

