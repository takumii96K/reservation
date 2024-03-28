package org.example.reservation.service.implement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Image;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.enumeration.Category;
import org.example.reservation.exception.ResourceNotFoundException;
import org.example.reservation.form.ProductForm;
import org.example.reservation.repository.JpaProductRepository;
import org.example.reservation.service.spec.ImageService;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.session.Cart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ImageService imageService;
	private final JpaProductRepository repository;

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
		MultipartFile file = form.getImage();
		Image savedImage = null;
		if (file != null && !file.isEmpty()) {
            try {
                savedImage = imageService.saveImage(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
		Product product = new Product();
		product.setProductName(form.getName());
		product.setProductPrice(form.getPrice());
		product.setStock(form.getStock());
		product.setImage(savedImage);
		product.setCategory(Category.valueOf(form.getCategory()));
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
		product.setProductPrice(form.getPrice());
		product.setCategory(Category.valueOf(form.getCategory()));

        return repository.save(product);
	}

	// 特定のカテゴリの製品を取得するメソッド
	public List<Product> getProductsByCategory(String category) {
		return repository.findProductsByCategory(Category.valueOf(category));
	}

}
