package org.example.reservation.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reservation.form.ProductForm;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long itemId;
    private String itemName;
    private BigDecimal price;
    private Integer quantity;

}