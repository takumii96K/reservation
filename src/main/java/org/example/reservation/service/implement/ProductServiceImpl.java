package org.example.reservation.service.implement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.converter.CategoryConverter;
import org.example.reservation.exception.ResourceNotFoundException;
import org.example.reservation.form.ProductForm;
import org.example.reservation.repository.JpaProductRepository;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.session.Cart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final JpaProductRepository repository;
	private final CategoryConverter converter;

	@Override
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	@Override
	@Transactional
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
	@Transactional
	public void createProduct(ProductForm form) {
		Product product = new Product();
		product.setProductName(form.getName());
		product.setProductPrice(form.getPrice());
		product.setStock(form.getStock());
		product.setImageUrl(form.getImgUrl());
		repository.save(product);
	}

	@Override
	public Product selectOneRandomProduct() {
		//ランダムでidの値を取得する
		Long id = repository.getRandomId();
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("その商品はありません。"));
	}

	@Override
	@Transactional
	public Product updateProduct(Long id, ProductForm form) throws ResourceNotFoundException {
		Product product = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

		product.setProductName(form.getName());
		product.setStock(form.getStock());
		product.setImageUrl(form.getImgUrl());
		product.setProductPrice(form.getPrice());

        return repository.save(product);
	}

	// 特定のカテゴリの製品を取得するメソッド
	public List<Product> getProductsByCategory(String category) {
		return repository.findProductsByCategory(converter.convertToEntityAttribute(category));
	}

}
