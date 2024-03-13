package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.form.CartItemRequest;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {


    private final ShoppingCartService service;

    @PostMapping("/cart/add")
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
