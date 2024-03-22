package org.example.reservation.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    private Long itemId;
    private String itemName;
    private BigDecimal price;
    private Integer quantity;
}