package org.example.reservation.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartSession;
import org.example.reservation.session.CheckoutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService service;
    private final CartSession session;

    /**
     * カートに追加ボタン
     * @param cartItemRequest request(ProductId,Quantity(UserInput))
     * @return responseパラメータ
     */
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest cartItemRequest) {
        try {
            service.addItemToCart(cartItemRequest); //カートに追加処理
            return ResponseEntity.ok().build(); //true
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("商品をカートに追加する際にエラーが発生しました: " + e.getMessage());
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(HttpSession httpsession ,@RequestBody CheckoutRequest request) {
        httpsession.setAttribute("request", request);
        httpsession.setAttribute("total", session.getCart().calculateTotalAmount());
        return ResponseEntity.ok(Map.of("redirectUrl", "/takeout/product/reservation"));
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
