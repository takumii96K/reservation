package org.example.reservation.service.spec;

import java.util.List;
import java.util.Optional;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.entity.projection.ProductProjection;
import org.example.reservation.form.ProductForm;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CheckoutRequest;

public interface ProductService {
	List<Product> getAllProducts();//商品を全件取得
	ProductProjection getProductProjectionById(Long id); //商品を1行取得
	Product getProductByRequest(CartItemRequest request);
	List<Product> getProductsByRequest(CheckoutRequest request);
	void updateProduct(ProductDto dto); //商品の情報を更新(在庫)
	void updateProductStock(CheckoutRequest request);
//	void saveProduct(Product product ,Order order);
	void createProduct(ProductForm form);
	Optional<Product> selectOneRandomProduct();//商品のランダム取得
}
