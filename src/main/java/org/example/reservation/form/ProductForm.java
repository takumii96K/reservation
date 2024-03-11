package org.example.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
	private List<ProductSelection> selections;

	@Data
	public static class ProductSelection {
		private Long id; // 商品のID
		private Integer quantity; // 選択された商品の数量
		private boolean selected; // 商品が選択されたかどうか
	}
}

