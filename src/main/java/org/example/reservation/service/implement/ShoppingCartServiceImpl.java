package org.example.reservation.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.enumeration.ReservationStatus;
import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.service.spec.OrderService;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartItem;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CartSession;
import org.example.reservation.session.CheckoutRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
    public void removeItemFromCart(Long id) {
        cartSession.getCart().removeItem(id);
    }

    @Override
    public void updateItemInCart(CartItemRequest request) {
        cartSession.getCart().updateItem(request.getItemId(), request.getQuantity());

    }

    /**
     * カートの合計を計算
     *
     * @return BigDecimal
     */
    @Override
    public BigDecimal calculateTotalPrice() {
        return cartSession.getCart().calculateTotalAmount();
    }

    /**
     * カートの中身を削除(カートそのものは保持）
     */
    @Override
    public void refreshCart() {
        cartSession.resetCart();
    }

    @Override
    public Map<Long, CartItem> lookInCart() {
        System.out.println("カートの中身を表示します");
        return cartSession.getCart().getItems();
    }

    @Override
    public void finalizeCheckout(CheckoutRequest request, ReservationInputForm form) {

        //reservationの新規作成
        Reservation createReservation = reservationService.createReservation(form);
        //statusを確定に変更
        createReservation.setStatus(ReservationStatus.CONFIRMED);
        //確定後在庫更新
        productService.updateProductStock(request);

        for (CartItemRequest cartItem : request.getCartItemRequests()) {
            Product product = productService.getProductByRequest(cartItem);
            orderService.createOrder(cartItem, product, createReservation);
        }

    }

}
