package org.example.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private Integer stock;
}
