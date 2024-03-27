package org.example.reservation.service.implement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.enumeration.ReservationStatus;
import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.service.spec.OrderService;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.Cart;
import org.example.reservation.session.CartItem;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CartSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductService productService;
    private final OrderService orderService;
    private final ReservationService reservationService;

    private final CartSession cartSession;
    private final ModelMapper modelMapper;

    @Override
    public void addItemToCart(CartItemRequest request) {
        //requestをitemへ変換
        CartItem item = modelMapper.map(request, CartItem.class);
        //カートに指定idの商品と個数を追加
        cartSession.getCart().addItem(item);
    }

    @Override
    public Map<Long, CartItem> lookInCart() {
        System.out.println("カートの中身を表示します");
        return cartSession.getCart().getItems();
    }

    @Override
    @Transactional
    public void finalizeCheckout(Cart cart, ReservationInputForm form) {
        // Reservationの新規作成
        Reservation createReservation = reservationService.createReservation(form);
        // Statusを確定に変更
        createReservation.setStatus(ReservationStatus.CONFIRMED);

        // 確定後、在庫を更新し、更新された製品情報を取得
        List<Product> updatedProducts = productService.updateProductStock(cart);

        // 更新された製品情報をもとに、注文を作成
        updatedProducts.forEach(updatedProduct -> {
            // カートから、現在処理している製品に対応するカートアイテムを取得
            CartItem cartItem = cart.getItems().get(updatedProduct.getProductId());

            // 注文サービスを使用して注文を作成
            if (cartItem != null) {
                orderService.createOrder(cartItem, updatedProduct, createReservation);
            }
        });
    }


    @Override
    public CartSession getSession() {
        return cartSession;
    }


}
