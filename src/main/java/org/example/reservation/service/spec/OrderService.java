package org.example.reservation.service.spec;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.Reservation;
import org.example.reservation.session.CartItem;

public interface OrderService {

    /**
     *  sessionのcartからorderを作成
     * @param item itemId/price/quantity/name
     * @param product idから検索されたproduct
     * @param reservation 新規登録されたreservationを使用する
     */
    void createOrder(CartItem item,
                     Product product, Reservation reservation);

}
