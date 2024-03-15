package org.example.reservation.entity.converter;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.projection.ProductProjection;
import org.example.reservation.session.CartItem;
import org.springframework.stereotype.Component;

@Component
public class IntoCartConverter{

    public Product convertToEntity(CartItem dto) {
        Product product = new Product();
        product.setProductId(dto.getItemId());
        product.setProductName(dto.getItemName());
        product.setProductPrice(dto.getPrice());
        return product;
    }

    public CartItem convertToDto(ProductProjection record) {
        CartItem item = new CartItem();
        item.setItemName(record.getProductName());
        item.setItemId(record.getProductId());
        item.setPrice(record.getProductPrice());
        return item;
    }
}
