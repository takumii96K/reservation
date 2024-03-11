package org.example.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long itemId;
    private String itemName;
    private BigDecimal price;
    private int quantity;

    public BigDecimal getTotalPrice(){
        int sum = price.intValue() * quantity;
        return BigDecimal.valueOf(sum);
    }
}