package org.example.reservation.entity.converter;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.dto.ProductDto;
import org.example.reservation.form.ProductForm;
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
        product.setProductName(form.getName());
        product.setStock(form.getStock());
        product.setProductPrice(form.getPrice());
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
        dto.setCategory(entity.getCategory());
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
            dto.setPrice(entity.getProductPrice());
            dto.setImage(entity.getImage());
            dto.setCategory(entity.getCategory());
            productDtoList.add(dto);
        }
        return productDtoList;
    }

}
