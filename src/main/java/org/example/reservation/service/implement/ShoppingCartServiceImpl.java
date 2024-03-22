package org.example.reservation.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Order;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.converter.IntoCartConverter;
import org.example.reservation.entity.converter.ProductDtoConverter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductService productService;
    private final OrderService orderService;
    private final ReservationService reservationService;

    private final CartSession cartSession;
    private final IntoCartConverter cartConverter;
    private final ModelMapper modelMapper;



    @Override
    public void addItemToCart(CartItemRequest request) {
        //requestをitemへ変換
        CartItem item = modelMapper.map(request, CartItem.class);
        //カートに指定idの商品と個数を追加
        cartSession.getCart().addItem(item);
    }


    /**
     * 商品をカートから削除
     *
     * @param id item.id
     */
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

        //リクエストから商品idに応じた各orderの作成
        List<Order> orders = orderService.createOrders(request);

        //productに各orderをjoin List<Product> savedProducts
        orders.forEach(order -> {
            productService.addOrder(request, order);
        });

        //reservationの登録
        Reservation temporaryReservation = reservationService.createTemporaryReservation(form);
        //statusを確定に変更
        temporaryReservation.setStatus(ReservationStatus.CONFIRMED);
        //reservation-orderのjoinを登録して、reservationを確定する
        reservationService.savedReservation(temporaryReservation,orders);
        //確定後在庫更新
        productService.updateProductStock(request);

    }





}
