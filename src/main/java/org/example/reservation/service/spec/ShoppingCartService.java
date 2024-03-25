package org.example.reservation.service.spec;

import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.session.Cart;
import org.example.reservation.session.CartItem;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CartSession;

import java.util.Map;

public interface ShoppingCartService {
    void addItemToCart(CartItemRequest request);
    Map<Long, CartItem> lookInCart();
    void finalizeCheckout(Cart cart, ReservationInputForm form);

    void deleteItem(CartItemRequest request);

    void updateQuantity(CartItemRequest request);

    CartSession getSession();
}
