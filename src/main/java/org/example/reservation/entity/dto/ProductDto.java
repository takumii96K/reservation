package org.example.reservation.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reservation.entity.Image;
import org.example.reservation.entity.enumeration.Category;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private Long id; // 商品のID
	private String productName; //商品名
	private BigDecimal price; //金額
	private int stock; //在庫
	private Image image;
	private Integer quantity; // 選択された商品の数量
	private Category category;
}

