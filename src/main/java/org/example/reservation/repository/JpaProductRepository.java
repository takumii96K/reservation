package org.example.reservation.repository;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.projection.ProductDto;
import org.example.reservation.entity.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long> {

    ProductProjection findProductProjectionByProductId(Long id);

    @Query("SELECT new org.example.reservation.entity.projection.ProductDto(p.productId, p.productName, p.stock, rp.quantity)FROM Product p JOIN Order rp")
    ProductDto findProductByProductId(Long id);

}
