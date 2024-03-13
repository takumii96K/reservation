package org.example.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reservation.entity.Product;
import org.example.reservation.session.CartItem;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
	private Long id; // 商品のID
	private String productName; //商品名
	private int productStock; //在庫
	private Integer quantity; // 選択された商品の数量
}

