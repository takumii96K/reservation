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
    public Product convertToEntity(ProductForm form) {
        Product product = new Product();
        form.setName(product.getProductName());
        form.setImgUrl(form.getImgUrl());
        form.setPrice(product.getProductPrice());
        form.setStock(product.getProductStock());
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
        dto.setProductStock(entity.getProductStock());
        return dto;
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
            dto.setProductStock(entity.getProductStock());
            productDtoList.add(dto);
        }
        return productDtoList;
    }
}
