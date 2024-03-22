package org.example.reservation.service.spec;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.Reservation;
import org.example.reservation.session.CartItemRequest;

public interface OrderService {

    /**
     * orderの新規作成
     * @param request POST:/checkout itemId/itemName/price/quantity
     * @param product
     * @param reservation
     */
    void createOrder(CartItemRequest request,
                     Product product, Reservation reservation);
}
