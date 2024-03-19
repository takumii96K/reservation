package org.example.reservation.session;

import lombok.Data;

import java.util.List;

@Data
public class CheckoutRequest {
    List<CartItem> items;
}
