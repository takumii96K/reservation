package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.service.spec.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/** Productコントローラ */
@Controller
@RequiredArgsConstructor
public class ProductController {


	private final ProductService service;
	private final ProductDtoConverter converter;

	@GetMapping("/takeout/product")
	public String showProductSelection(Model model, @RequestParam(required = false) String category) {
		List<ProductDto> products;
		// カテゴリが指定されている場合は、そのカテゴリの商品のみを取得
		if (category != null && !category.isEmpty()) {
			products = converter.convertToDtoList(service.getProductsByCategory(category));
		} else {
			// カテゴリが指定されていない場合は、全商品情報を取得
			products = converter.convertToDtoList(service.getAllProducts());
		}
		model.addAttribute("products", products);
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
