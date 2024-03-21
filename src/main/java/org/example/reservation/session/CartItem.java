package org.example.reservation.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long itemId;
    private Integer quantity;
    private String itemName;
    private BigDecimal price;
}