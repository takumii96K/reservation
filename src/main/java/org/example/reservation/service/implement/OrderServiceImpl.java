package org.example.reservation.service.implement;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.reservation.ResourceNotFoundException;
import org.example.reservation.entity.Order;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.Reservation;
import org.example.reservation.repository.JpaOrderRepository;
import org.example.reservation.repository.JpaProductRepository;
import org.example.reservation.repository.JpaReservationRepository;
import org.example.reservation.service.spec.OrderService;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CheckoutRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final JpaOrderRepository orderRepository;
    private final JpaProductRepository productRepository;

    @Override
    public List<Order> createOrders(CheckoutRequest request) {
        List<Order> orders = new ArrayList<>();
        request.getCartItemRequests().forEach(items ->{
            Order order = createOrder(items);
            orders.add(order);

        });
        return orders;
    }

    @Override
    public Order createOrder(CartItemRequest request) {
        Order order = new Order();
        order.setQuantity(request.getQuantity());
        return order;
    }
}
