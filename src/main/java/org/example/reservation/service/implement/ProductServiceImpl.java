package org.example.reservation.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.example.reservation.ResourceNotFoundException;
import org.example.reservation.entity.Order;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.entity.projection.ProductProjection;
import org.example.reservation.repository.JpaProductRepository;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.session.CheckoutRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final JpaProductRepository repository;
	private final ProductDtoConverter converter;

	@Override
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	@Override
	public ProductProjection getProductProjectionById(Long id) {
		return repository.findProductProjectionByProductId(id);
	}

	@Override
	public Product getProductById(Long id) {
		return repository.findProductByProductId(id);
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
	public void addOrder(CheckoutRequest request, Order order) {

		request.getCartItemRequests().forEach(item ->{
			Product product = repository.findById(item.getItemId()).orElseThrow(() -> new ResourceNotFoundException("指定の商品はありません"));
			if(product.getOrders() == null){
				product.setOrders(new ArrayList<>());
			}
			product.getOrders().add(order);
			repository.save(product);
		});

	}


}
