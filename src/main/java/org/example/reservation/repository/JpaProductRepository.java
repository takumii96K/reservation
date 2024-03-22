package org.example.reservation.repository;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.entity.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long> {

    ProductProjection findProductProjectionByProductId(Long id);

    @Query("SELECT new org.example.reservation.entity.Product(p.productId, p.productName, p.imageUrl, p.productPrice, p.stock) FROM Product p WHERE p.productId = :id")
    Product findProductByProductId(@Param("id") Long id);

//    @Query("SELECT new org.example.reservation.entity.projection.ProductDto" +
//            "(p.productId, p.productName, p.productPrice, p.stock, rp.quantity)FROM Product p JOIN Order rp")
//    ProductDto findProductDtoByProductId(Long id);

}
