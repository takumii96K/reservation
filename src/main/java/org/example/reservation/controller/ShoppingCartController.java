package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CheckoutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService service;

    /**
     * カートに追加ボタン
     * @param cartItemRequest request(ProductId,Quantity(UserInput))
     * @return responseパラメータ
     */
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest cartItemRequest) {
        System.out.println("メソッド起動");
        try {
            service.addItemToCart(cartItemRequest.getProductId(), cartItemRequest.getQuantity());
            System.out.println("成功");
            return ResponseEntity.ok().build(); //true
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("商品をカートに追加する際にエラーが発生しました: " + e.getMessage());
        }
    }
    @PostMapping("/checkout")
    public ResponseEntity<?> processCheckout(@RequestBody CheckoutRequest request) {
        System.out.println(request);
        service.checkout(request);
        // 決済処理のロジックをここに実装します。
        // 例えば、カートの情報を取得し、決済を行い、結果を返すなど。
        try {
            // 決済サービスを呼び出す
            // checkoutService.process(checkoutRequest);
            // 決済が成功した場合
            return ResponseEntity.ok().body("決済が完了しました。");
        } catch (Exception e) {
            // エラーハンドリング
            return ResponseEntity.badRequest().body("決済処理に失敗しました。");
        }
    }

//}

//カートに追加ボタンを押すたびに特定の商品をセッションに追加する
//    @PostMapping("/cart/add")
//    public String addToCart(@ModelAttribute("inputProduct") ProductForm form, Model model) {
//        //(form)に埋め込まれた指定idの商品を取得
//        Product product = service.getProductById(form.getProductId()); //idがこないhtmlに問題あり
//        //productをcartItemとしてCartに追加する
//        session.getCart().addItem(service.registerCartItem(product, form));
//        model.addAttribute("cart", session);
//        return "redirect:/takeout/product";
//    }

//    /**
//     * クライアントへレスポンスするオブジェクト
//     */
//    @Data
//    @AllArgsConstructor
//    public static class CartResponse {
//        private List<CartItem> items;
////        private int itemCount = items.size();
////        private BigDecimal totalPrice;
//
//    }
//}
    }
