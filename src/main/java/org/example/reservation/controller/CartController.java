//package org.example.reservation.controller;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.example.reservation.entity.Product;
//import org.example.reservation.form.Cart;
//import org.example.reservation.form.CartItem;
//import org.example.reservation.service.spec.ProductService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class CartController {
//
//    private final ProductService service;
//
//    /**
//     * カートに追加ボタンを押すたびにajaxで実行される通信メソッド
//     * @param itemRequest ProductId,quantity
//     * @param session cart
//     * @return response
//     */
//    @PostMapping("/cart/add")
//    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest itemRequest, HttpSession session) {
//
//        // 指定idの商品情報を取得
//        Product product = service.getProductById(itemRequest.getProductId());
//        if (product == null) {
//            return ResponseEntity.notFound().build();
//        }
//        //セッションcartオブジェクトの新規作成(すでにある場合は作成しない)
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart == null) {
//            cart = new Cart();
//        }
//
////        //カートに入れるアイテム情報をセットする
////        //Productに対してCartItemがdtoとして機能する
////        //id/商品名/金額/個数
////        CartItem newItem = new CartItem();
////        newItem.setProductId(product.getProductId());
////        newItem.setProductName(product.getProductName());
////        newItem.setPrice(BigDecimal.valueOf(product.getProductPrice()));
////        newItem.setQuantity(itemRequest.getQuantity());
////        cart.addItem(newItem);
////
////        //セッションにCertリストを"cart”属性として保持する
////        session.setAttribute("cart", cart);
////
////        // カートの情報をクライアントに返す
////        CartResponse cartResponse = new CartResponse(cart.getItems(), cart.getItemCount(),cart.getTotalPrice());
////        return ResponseEntity.ok(cartResponse);
//    }
//
//
//    /**
//     * クライアントからのPOSTリクエストbodyに入ってくるオブジェクト
//     */
//    @Data
//    public static class CartItemRequest {
//        private Long itemId;
//        private Integer quantity;
//    }
//
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
