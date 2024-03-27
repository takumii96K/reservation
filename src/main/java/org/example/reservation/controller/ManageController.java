package org.example.reservation.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.exception.ResourceNotFoundException;
import org.example.reservation.form.ProductForm;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.example.reservation.service.spec.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ManageController {
	/** DI対象 */
	private final ProductService productService;
	private final UserService userService;
	private final ReservationService reservationService;
	private final ProductDtoConverter converter;

	// 商品情報の編集フォーム表示フラグ
	@Getter
	private boolean productFormVisible = false;

	// 商品情報の編集フォーム表示フラグのリセット
	private void resetProductFormVisible() {
		productFormVisible = false;
	}


	/**
	 * 商品情報,ユーザー情報,予約情報の一覧を表示
	 * @param model products:ProductDto, users, reservation
	 * @return view: manage.html
	 */
	@GetMapping("/manage")
	public String showList(Model model) {
		//商品情報
		model.addAttribute("products" ,converter.convertToDtoList(productService.getAllProducts()));
		// 商品情報の編集フォーム表示フラグ
		model.addAttribute("productFormVisible", this.productFormVisible);
		model.addAttribute("form", new ProductForm());
		//authorityKindが1のユーザーのみを取得
		model.addAttribute("users", userService.findUserWithAuthorityKindOne());
		//予約情報
		model.addAttribute("reservations",reservationService.getReservationProductDtoAll());
		return "/manage";
	}


	/** 商品情報の編集処理 */
	// 商品情報の更新を行うメソッド
	@PutMapping("/edit/{id}")
	@ResponseBody // RESTful な応答をこのメソッドから送る
	public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody ProductForm form) {
		try {
			Product updatedProduct = productService.updateProduct(id, form);
			return ResponseEntity.ok(updatedProduct);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("更新に失敗しました: " + e.getMessage());
		}
	}


	/** 商品情報の追加処理 */
	@PostMapping("/manage/add-product")
	public String addProduct(@ModelAttribute("form") ProductForm form, Model model) {
		try {
			productService.createProduct(form);
			model.addAttribute("successMessage", "登録できました！");

		} catch (Exception e) {
			model.addAttribute("errorMessage", "登録できませんでした");
			model.addAttribute("form", form); // 入力されたフォームデータを再表示するために必要な場合
		}

		// 商品情報, ユーザー情報, 予約情報の再取得とビューへの追加
		model.addAttribute("products", converter.convertToDtoList(productService.getAllProducts()));
		model.addAttribute("productFormVisible", this.productFormVisible);
		model.addAttribute("form", new ProductForm());
		model.addAttribute("users", userService.findUserWithAuthorityKindOne());
		model.addAttribute("reservations", reservationService.getReservationProductDtoAll());

		return "/manage"; // 商品登録画面にフォワード
	}


}