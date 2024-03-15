package org.example.reservation.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.reservation.form.ProductForm;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/** Productコントローラ */
@Controller
@RequiredArgsConstructor
@RequestMapping("/takeout")
public class ProductController {

	private  final ProductService service;
	private final ShoppingCartController controller;
//	private final CartSession cartSession;

	// 商品選択画面を表示
	@GetMapping("/product")
	public String showProductSelection(Model model) {
		// 全商品情報を取得してModelに追加
		model.addAttribute("products", service.getAllProducts());
		// フォームオブジェクトを初期化してModelに追加
		model.addAttribute("inputProduct", new ProductForm());
		System.out.println(Objects.isNull(controller));
		return "/product"; // 商品選択ページのビュー名
	}

	@GetMapping("/top")
	public String goTopPage(){
		return "/top";
	}

	@PostMapping("/confirm")
	public String handleProductSelection(@ModelAttribute("ProductSelection") ProductForm form, HttpSession session) {
		// 選択された商品情報をセッションに保存
//		session.setAttribute("selectedProducts", form.getSelections());se
		// 次のステップ（ユーザー情報入力画面）にリダイレクト
		return "/reservation";
	}

	@GetMapping("/product/member")
	public String memberPage(){
		return "/product_member";
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
