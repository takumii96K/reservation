package org.example.reservation.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.reservation.ResourceNotFoundException;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.entity.projection.ProductProjection;
import org.example.reservation.form.ProductForm;
import org.example.reservation.repository.JpaProductRepository;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.session.CartItemRequest;
import org.example.reservation.session.CheckoutRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final JpaProductRepository repository;
	@Override
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	@Override
	public ProductProjection getProductProjectionById(Long id) {
		return repository.findProductProjectionByProductId(id);
	}

	@Override
	public Product getProductByRequest(CartItemRequest cartItemRequest) {
		return repository.findById(cartItemRequest.getItemId())
				.orElseThrow(() -> new ResourceNotFoundException("指定の商品はありません" + cartItemRequest.getItemId()));
	}

	public List<Product> getProductsByRequest(CheckoutRequest request) {
		return request.getCartItemRequests().stream()
				.map(cartItemRequest -> repository.findById(cartItemRequest.getItemId())
						.orElseThrow(() -> new ResourceNotFoundException("指定の商品はありません")))
				.collect(Collectors.toList());
	}

	@Override
	public void updateProduct(ProductDto dto) {

		Product product = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("指定の商品はありません"));

			product.setProductName(dto.getProductName());
			product.setProductPrice(dto.getPrice());
			product.setStock(dto.getStock());
			repository.save(product);
	}

	@Override
	public void updateProductStock(CheckoutRequest request) {
		request.getCartItemRequests().forEach(items ->{
			Product product = repository.findById(items.getItemId()).orElseThrow(() -> new ResourceNotFoundException("指定の商品はありません"));
			product.setStock(product.getStock() - items.getQuantity());
			repository.save(product);
		});

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
