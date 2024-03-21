package org.example.reservation.controller;

import java.util.Objects;

import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.service.spec.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;


/** Productコントローラ */
@Controller
@RequiredArgsConstructor
public class ProductController {


	private  final ProductService service;
	private final ShoppingCartController controller;

	// 商品選択画面を表示
	@GetMapping("/takeout/product")
	public String showProductSelection(Model model) {
		// 全商品情報を取得してModelに追加
		model.addAttribute("products", service.getAllProducts());
		// フォームオブジェクトを初期化してModelに追加
		model.addAttribute("inputProduct", new ProductDto());
		System.out.println(Objects.isNull(controller));
		return "/product"; // 商品選択ページのビュー名
	}

//	@PostMapping("/confirm")
//	public String handleProductSelection(@ModelAttribute("ProductSelection") ProductDto form, HttpSession session) {
//		// 選択された商品情報をセッションに保存
////		session.setAttribute("selectedProducts", form.getSelections());se
//		// 次のステップ（ユーザー情報入力画面）にリダイレクト
//		return "/reservation";
//	}
}
