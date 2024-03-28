package org.example.reservation.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ManageController {
	/** DI対象 */
	private final ProductService productService;
	private final UserService userService;
	private final ReservationService reservationService;
	private final ProductDtoConverter converter;


	/**
	 * 商品情報,ユーザー情報,予約情報の一覧を表示
	 * @param model products:ProductDto, users, reservation
	 * @return view: manage.html
	 */
	@GetMapping("/manage")
	public String showList(Model model) {
		//商品情報
		model.addAttribute("products" ,converter.convertToDtoList(productService.getAllProducts()));
		model.addAttribute("form", new ProductForm());
		//authorityKindが1のユーザーのみを取得
		model.addAttribute("users", userService.findUserWithAuthorityKindOne());
		//予約情報
		model.addAttribute("reservations",reservationService.getReservationProductDtoAll());
		return "/manage";
	}


	/** 商品情報の編集処理 */
	// エラーが出る、formのimagefileの扱いを変えないのが原因
	@PutMapping("/edit/{id}")
	@ResponseBody
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
	public String addProduct(@ModelAttribute("form") ProductForm form, RedirectAttributes redirectAttributes) {
		try {
			productService.createProduct(form);
			redirectAttributes.addFlashAttribute("successMessage", "登録できました！");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "登録できませんでした");
			redirectAttributes.addFlashAttribute("form", form); // 入力されたフォームデータを再表示するために必要な場合
		}

		return "redirect:/admin/manage"; // 商品登録画面にフォワード
	}

}