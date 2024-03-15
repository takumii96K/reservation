package org.example.reservation.service.spec;

import org.example.reservation.session.CartItem;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {
    void addItemToCart(Long itemId, int quantity);
    void removeItemFromCart(Long id);
    BigDecimal calculateTotalPrice();
    void refreshCart();
    Map<Long, CartItem> lookInCart();

    void submitShopping();
}
