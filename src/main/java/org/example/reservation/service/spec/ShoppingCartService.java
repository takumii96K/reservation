package org.example.reservation.service.spec;

import org.example.reservation.session.CartItem;
import org.example.reservation.session.CheckoutRequest;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {
    void addItemToCart(Long itemId, int quantity);
    void removeItemFromCart(Long id);
    BigDecimal calculateTotalPrice();
    void refreshCart();
    Map<Long, CartItem> lookInCart();
    void checkout(CheckoutRequest request);

    void submitShopping(CheckoutRequest request);
}
