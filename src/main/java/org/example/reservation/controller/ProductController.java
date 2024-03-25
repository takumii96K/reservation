package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.service.spec.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** Productコントローラ */
@Controller
@RequiredArgsConstructor
public class ProductController {


	private final ProductService service;
	private final ProductDtoConverter converter;

	// 商品選択画面を表示
	@GetMapping("/takeout/product")
	public String showProductSelection(Model model) {
		// 全商品情報を取得してModelに追加
		model.addAttribute("products", converter.convertToDtoList(service.getAllProducts()));
		// フォームオブジェクトを初期化してModelに追加
		model.addAttribute("inputProduct", new ProductDto());
		return "/product"; // 商品選択ページのビュー名
	}

	/**メニューデータをランダムで1件取得し、画面に表示する*/
	@PostMapping("/random")
	public String showRandom(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("randomProductName",
				converter.convertToDto(service.selectOneRandomProduct()).getProductName());
        return "redirect:/takeout/product";
    }

}
