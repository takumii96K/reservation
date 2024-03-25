package org.example.reservation.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Order;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.Reservation;
import org.example.reservation.repository.JpaOrderRepository;
import org.example.reservation.service.spec.OrderService;
import org.example.reservation.session.CartItem;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final JpaOrderRepository repository;

    @Override
    public void createOrder(CartItem item, Product product, Reservation reservation) {
        Order order = new Order();
        order.setQuantity(item.getQuantity());
        order.setProduct(product);
        order.setReservation(reservation);
        repository.save(order);
    }

}
