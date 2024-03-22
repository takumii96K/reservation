package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CartSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> checkout() {
        if(session.getCart().getItems().isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("message", "商品をカートに入れてください"));
        }
        return ResponseEntity.ok(Map.of("redirectUrl", "/takeout/product/reservation"));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteItem(@RequestBody CartItemRequest cartItemRequest){
        try {
            service.deleteItem(cartItemRequest);//カートから指定idの商品を削除
            return ResponseEntity.ok().build(); //true
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("商品をカートに追加する際にエラーが発生しました: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateQuantity(@RequestBody CartItemRequest cartItemRequest){
        try {
            service.updateQuantity(cartItemRequest);//カートから指定idの商品を更新
            return ResponseEntity.ok().build(); //true
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("カートを更新する際にエラーが発生しました: " + e.getMessage());
        }
    }


}
