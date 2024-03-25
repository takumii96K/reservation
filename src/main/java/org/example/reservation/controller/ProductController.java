package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.service.spec.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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

//	@PostMapping("/confirm")
//	public String handleProductSelection(@ModelAttribute("ProductSelection") ProductDto form, HttpSession session) {
//		// 選択された商品情報をセッションに保存
////		session.setAttribute("selectedProducts", form.getSelections());se
//		// 次のステップ（ユーザー情報入力画面）にリダイレクト
//		return "/reservation";
//	}


	/**メニューデータをランダムで1件取得し、画面に表示する*/
//	@GetMapping("/takeout/product22")
//	public String showRandom(ProductForm form, Model model) {
//		//商品を取得
//		Optional<Product> productOpt = service.selectOneRandomProduct();
//		//値が入っているか判定する
//		if(productOpt.isPresent()) {
//			// ProductForm productForm = productOpt(productOpt.get());
//	        //    model.addAttribute("productForm", productForm);
//	            form.setName(productOpt.get().getProductName());
//		}else {
//			model.addAttribute("msg", "商品がありません…");
//			return "takeout/product";
//		}
//        return "takeout/product";
//    }
//		//値が入っているか判定する
//		if(productOpt.isPresent()) {
//			 ProductForm productForm = makeProductForm(productOpt.get());
//	            model.addAttribute("productForm", productForm);
//		}else {
//			model.addAttribute("msg", "商品がありません…");
//			return "takeout/product";
//		}

        // 商品がある場合は該当のビューを返す
//        return "takeout/product";
//    }
//
//	//表示用「Model」への格納
//		model.addAttribute("productForm", productForm);
//		return "takeout/product";

//     // ランダムに商品を選択する
//     Random random = new Random();
//     int randomIndex = random.nextInt(allProducts.size());
//    // return allProducts.get(randomIndex);
//     String randomProductName = allProducts.get(randomIndex).getProductName();
//     return ResponseEntity.ok(randomProductName);
//
//	 @PostMapping("/takeout/product") // POSTメソッドをサポートするように変更
//	    public ResponseEntity<?> getRandomProduct(@RequestParam("someParameter") String someParameter) {
//	        List<Product> allProducts = service.getAllProducts();
//	        if (allProducts.isEmpty()) {
//	            // 商品が存在しない場合はエラーメッセージを返す
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found");
//	        }
////	        @GetMapping("/takeout/product22")
////	        public ResponseEntity<String> getRandomProduct() {
////	            // ProductServiceを使用してすべての商品を取得する
////	            List<Product> allProducts = service.getAllProducts();
////
////	            if (allProducts.isEmpty()) {
////	                // 商品が存在しない場合はエラーメッセージを返す
////	                return ResponseEntity.notFound().build();
//	//            }
////	// 商品をランダムに取得するエンドポイント
//// @GetMapping("/random-product")
//// public Product getRandomProduct() {
////     List<Product> allProducts = service.getAllProducts();
////     if (allProducts.isEmpty()) {
////         // 商品が存在しない場合はnullを返すか、適切なエラー処理を行う
////         return null;
////     }
// }
//// @GetMapping("/popup")
//// public String showPopup(Model model) {
////     return "popup"; // popup.htmlを表示する
//// }
}
