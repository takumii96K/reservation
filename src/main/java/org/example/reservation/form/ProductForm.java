package org.example.reservation.form;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm { //入力用
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private Integer stock;

}

