package org.example.reservation.service.spec;

import java.util.List;
import java.util.Optional;

import org.example.reservation.entity.Product;

public interface ProductService
{
	List<Product> getAllProducts(); 						//商品を全件取得
	Optional<Product> getProductsByIds(Long id);    		//商品を1行取得
	Product updateProduct(Long id, Product productDetails); //商品の情報を更新(在庫)
	Product getProductById(Long id); 						//商品idから商品情報を取得
}
