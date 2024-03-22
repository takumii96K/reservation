package org.example.reservation.service.spec;

import java.util.List;

import org.example.reservation.ResourceNotFoundException;
import org.example.reservation.entity.Order;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.entity.projection.ProductProjection;
import org.example.reservation.session.CheckoutRequest;

public interface ProductService {
	List<Product> getAllProducts();//商品を全件取得
	ProductProjection getProductProjectionById(Long id); //商品を1行取得
	Product getProductById(Long id);
	void updateProduct(ProductDto dto); //商品の情報を更新(在庫)
	void updateProductStock(CheckoutRequest request);

	void addOrder(CheckoutRequest request, Order order);

}
