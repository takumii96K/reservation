package org.example.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private String name;
    private MultipartFile image;
    private BigDecimal price;
    private Integer stock;
    private String category;
}
