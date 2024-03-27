package org.example.reservation.service.spec;

import org.example.reservation.entity.Product;
import org.example.reservation.form.ProductForm;
import org.example.reservation.session.Cart;

import java.util.List;

public interface ProductService {

	/**
	 * productの全建取得
	 * @return 商品リスト
	 */
	List<Product> getAllProducts();
	List<Product> updateProductStock(Cart cart);
	void createProduct(ProductForm form);
	Product selectOneRandomProduct();//商品のランダム取得
	Product updateProduct(Long id, ProductForm from);
}
