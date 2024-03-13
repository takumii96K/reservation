package org.example.reservation.controller;

import java.time.LocalDateTime;

import org.example.reservation.form.ReservationForm;
import org.example.reservation.service.spec.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	//入力した名前と電話番号を受け取る⇔Formに入る

	//注文内容を確認する
	@GetMapping("/reservation")
	public String showReservationForm(Model model)
	{
		model.addAttribute("reservationForm", new ReservationForm());
		return "/reservation";
	}

	@PostMapping("/confirm")
	public String showConfirm(@ModelAttribute("inputReservationForm") ReservationForm form, Model model) 
	{
		// ここでフォームのデータを使用した処理を実行します。
		// 例えば、予約データをデータベースに保存するなど。

		//setReservationRegisterの戻り値がtrueの場合
		if(reservationService.setReservationRegister(form))
		{
			return "/confirmation";//結果表示ページを戻り値として返す
		}
		else
		{
			return "redirect:/takeout/product/reservation";//予約画面のページを戻り値として返す
		}
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
	public String completeOrderView(ReservationForm reservationForm)
	{
		
		reservationService.setReservationRegister(reservationForm);
		
		return "confirmation";
		
	}
	
	

}

