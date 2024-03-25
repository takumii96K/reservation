package org.example.reservation.service.implement;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.reservation.ResourceNotFoundException;
import org.example.reservation.entity.Product;
import org.example.reservation.form.ProductForm;
import org.example.reservation.repository.JpaProductRepository;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.session.Cart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final JpaProductRepository repository;
	@Override
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	@Override
	public List<Product> updateProductStock(Cart cart) {
		List<Product> updatedProducts = new ArrayList<>();

		cart.getItems().forEach((productId, cartItem) -> {
			// productId は商品ID、cartItem はカートアイテム
			Product product = repository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("指定の商品はありません"));
			product.setStock(product.getStock() - cartItem.getQuantity()); // cartItemから数量を取得
			updatedProducts.add(repository.save(product)); // 更新された製品をリストに追加
		});

		return updatedProducts; // 更新された製品のリストを返す
	}

	@Override
	public void createProduct(ProductForm form) {
		Product product = new Product();
		product.setProductName(form.getName());
		product.setProductPrice(form.getPrice());
		product.setStock(form.getStock());
		product.setImageUrl(form.getImgUrl());
		repository.save(product);
	}

	@Override
	public Optional<Product> selectOneRandomProduct() {
		//ランダムでidの値を取得する
		Long randId = repository.getRandomId();
		//問題がない場合
		if(randId == null) {
			//空のOptional.empty();
			return Optional.empty();
		}

		//名前を取ってくる
		return repository.findById(randId);
	}

}
