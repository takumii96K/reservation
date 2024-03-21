package org.example.reservation.service.spec;

import org.example.reservation.entity.Order;
import org.example.reservation.entity.Reservation;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CheckoutRequest;

import java.util.List;

public interface OrderService {

    List<Order> createOrders(CheckoutRequest request);
    Order createOrder(CartItemRequest request);
}
