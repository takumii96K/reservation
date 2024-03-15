package org.example.reservation.repository;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long> {

    ProductProjection findProductProjectionByProductId(Long id);

}
