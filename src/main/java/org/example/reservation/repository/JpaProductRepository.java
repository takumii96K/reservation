package org.example.reservation.repository;

import org.example.reservation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.productId, p.productName, p.imageUrl, p.productPrice, p.stock FROM Product p WHERE p.productId = :id")
    Product findProductByProductId(@Param("id") Long id);

    @Query("SELECT p.productId FROM Product p ORDER BY RANDOM() limit 1")
    Long getRandomId();
}

