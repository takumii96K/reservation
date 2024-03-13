package org.example.reservation.entity.converter;

import org.example.reservation.entity.Product;
import org.example.reservation.session.CartItem;
import org.springframework.stereotype.Component;

@Component
public class IntoCartConverter implements Converter<CartItem, Product> {
    @Override
    public Product convertToEntity(CartItem dto) {
        Product product = new Product();
        product.setProductId(dto.getItemId());
        product.setProductName(dto.getItemName());
        product.setProductPrice(dto.getPrice());
        return product;
    }

    @Override
    public CartItem convertToDto(Product entity) {
        return null;
    }
}
