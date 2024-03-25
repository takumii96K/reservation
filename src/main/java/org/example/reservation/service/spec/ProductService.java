package org.example.reservation.service.spec;

import java.util.List;
import java.util.Optional;

import org.example.reservation.entity.Product;
import org.example.reservation.form.ProductForm;
import org.example.reservation.session.Cart;

public interface ProductService {

	/**
	 * productの全建取得
	 * @return 商品リスト
	 */
	List<Product> getAllProducts();

	List<Product> updateProductStock(Cart cart);
	void createProduct(ProductForm form);
	Optional<Product> selectOneRandomProduct();//商品のランダム取得
}
