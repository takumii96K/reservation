package org.example.reservation.entity.converter;

import org.example.reservation.entity.Product;
import org.example.reservation.form.ProductForm;
import org.example.reservation.entity.projection.ProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDtoConverter{
    /**
     * 入力フォームからEntityオブジェクト変換
     * @param form ProductForm
     * @return Product
     */
    public Product convertToEntityFromForm(Product form) {
        Product product = new Product();
        form.setProductName(product.getProductName());
        form.setImageUrl(form.getImageUrl());
        form.setProductPrice(product.getProductPrice());
        form.setStock(product.getStock());
        return product;
    }

    /**
     * Entityから変換するDtoオブジェクト
     * @param entity Product
     * @return ProductDto
     */
    public ProductDto convertToDto(Product entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getProductId());
        dto.setProductName(entity.getProductName());
        dto.setStock(entity.getStock());
        return dto;
    }

    public Product convertToEntity(ProductDto dto){
        Product product = new Product();
        product.setProductId(dto.getId());
        product.setProductName(dto.getProductName());
        product.setStock(dto.getStock());
        return product;
    }

    /**
     * DBから全件取得したDtoリスト
     * @param allEntity List<Product>
     * @return List<ProductDto>
     */
    public List<ProductDto> convertToDtoList(List<Product> allEntity) {
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product entity : allEntity) {
            ProductDto dto = new ProductDto();
            dto.setId(entity.getProductId());
            dto.setProductName(entity.getProductName());
            dto.setStock(entity.getStock());
            productDtoList.add(dto);
        }
        return productDtoList;
    }
}
