package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.form.ReservationForm;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//予約内容確認画面コントローラ
@Controller
@RequestMapping("/takeout/product")
@RequiredArgsConstructor
public class ReservationController {
	// DI対象

	private final ProductService service;
	private final ReservationService reservationService;

	//注文内容を確認する
	@GetMapping("/reservation")
	public String showReservationForm(Model model) {
		model.addAttribute("inputReservationForm", new ReservationForm());
		return "reservation";
	}

    @PostMapping("/confirm")
    public String showConfirm(@ModelAttribute("inputReservationForm") ReservationForm form, Model model) {
		// ここでフォームのデータを使用した処理を実行します。
		// 例えば、予約データをデータベースに保存するなど。

		// 処理が完了したら、結果表示ページなどにリダイレクトする
		return "confirmation";
    }
//
//	/** 確定 */
//	public String confirmReservation(@ModelAttribute ReservationForm form, Model model) {
//        // ここで予約情報をデータベースに保存する処理を追加します
//        // 例: service.saveReservation(form);
//
//        // 予約完了ページやリストページにリダイレクト
//        return "redirect:/reservation/confirm";
//	}
}
