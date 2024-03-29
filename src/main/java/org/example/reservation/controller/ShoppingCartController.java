package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        try {
            service.addItemToCart(cartItemRequest); //カートに追加処理
            return ResponseEntity.ok().build(); //true
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("商品をカートに追加する際にエラーが発生しました: " + e.getMessage());
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout() {
        if(service.getSession().getCart().getItems().isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("message", "商品をカートに入れてください"));
        }
        return ResponseEntity.ok(Map.of("redirectUrl", "/takeout/product/reservation"));
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long itemId) {
        // 受け取ったIDを使用して商品の削除を処理
    	service.getSession().getCart().removeItem(itemId);
        // 適切なレスポンスを返します
        return ResponseEntity.ok("商品が正常に削除されました");
    }

    @PutMapping("/update/{itemId}/{itemQuantity}")
    public ResponseEntity<?> updateProduct(@PathVariable Long itemId, @PathVariable int itemQuantity)
    {
        // 商品の更新処理
        service.getSession().getCart().updateItem(itemId, itemQuantity);
        // 適切なレスポンスを返します
        return ResponseEntity.ok("商品が正常に更新されました");
    }

    }
