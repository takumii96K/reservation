package org.example.reservation.service.spec;

import java.math.BigDecimal;

public interface ShoppingCartService {
    void addItemToCart(Long itemId, int quantity);
    void removeItemFromCart(Long id);
    BigDecimal calculateTotalPrice();
    void refreshCart();
}
