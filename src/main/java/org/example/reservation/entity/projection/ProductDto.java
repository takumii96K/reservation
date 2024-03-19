package org.example.reservation.entity.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private Long id; // 商品のID
	private String productName; //商品名
	private int stock; //在庫
	private Integer quantity; // 選択された商品の数量
}

