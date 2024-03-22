package org.example.reservation.entity.projection;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto { //データベースから取り出す用
	private Long id; // 商品のID
	private String productName; //商品名
	private int productStock; //在庫
	private BigDecimal productPrice; //商品価格
	private Integer quantity; // 選択された商品の数量
}
