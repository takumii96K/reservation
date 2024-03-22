package org.example.reservation.service.spec;

import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.session.CartItem;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CheckoutRequest;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {
    void addItemToCart(CartItemRequest request);
    void removeItemFromCart(Long id);
    void updateItemInCart(CartItemRequest request);
    BigDecimal calculateTotalPrice();
    void refreshCart();
    Map<Long, CartItem> lookInCart();
    void finalizeCheckout(CheckoutRequest request, ReservationInputForm form);
}
