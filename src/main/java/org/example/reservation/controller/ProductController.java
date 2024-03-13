package org.example.reservation.controller;

import org.example.reservation.form.ProductForm;
import org.example.reservation.service.spec.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/team


/** Productコントローラ */
@Controller
@RequiredArgsConstructor
@RequestMapping("/takeout/top")
public class ProductController {

	private final ProductService service;

	// ホームページを表示
	@GetMapping
	public String showProductSelection(Model model) {
		// 全商品情報を取得してModelに追加
		model.addAttribute("products", service.getAllProducts());
		// フォームオブジェクトを初期化してModelに追加
		model.addAttribute("ProductSelection", new ProductForm());
		return "/product"; // ホームページのビュー名
	}

	@PostMapping//("/confirm")
	public String handleProductSelection(@ModelAttribute("ProductSelection") ProductForm form, HttpSession session) {
		// 選択された商品情報をセッションに保存
		session.setAttribute("selectedProducts", form.getSelections());
		// 次のステップ（ユーザー情報入力画面）にリダイレクト
		return "/reservation";
	}
	
	// メンバーページを表示
	@GetMapping("/member")
	public String showProductMember(Model model) {
	    // メンバーページのビュー名を返す
	    return "/product-member";
	}
	

//	@GetMapping("/reservation")
//	public String showReservation(Model model, HttpSession session) {
//		// セッションから選択された商品情報を取得
//		List<ProductForm.ProductSelection> selectedProducts = (List<ProductForm.ProductSelection>) session.getAttribute("selectedProducts");
//		model.addAttribute("selectedProducts", selectedProducts);
//
//		// 取得した商品情報をモデルに追加
//		if (selectedProducts != null) {
//			model.addAttribute("selectedProducts", selectedProducts);
//		} else {
//			// エラーハンドリング、例えば選択された商品がない場合
//			model.addAttribute("error", "商品が選択されていません。");
//			// または、商品選択画面にリダイレクトさせる
//			return "redirect:/takeout/product";
//		}

		// ユーザー情報入力用のフォームオブジェクトをモデルに追加
//		model.addAttribute("userInfo", new UserInfo());

		// レジデーションビューを返す
//		return "reservation";
//	}

}
