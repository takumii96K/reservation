package org.example.reservation.entity.converter;

import org.example.reservation.controller.ProductController;
import org.example.reservation.entity.Product;
import org.example.reservation.form.ProductForm;

public class ProductFromConverter implements Converter<ProductForm, Product>{
    @Override
    public Product convertToEntity(ProductForm form) {
        return null;
    }

    @Override
    public ProductForm convertToForm(Product entity) {
//        ProductForm form = new ProductForm();
//        form.setId(entity.getProductId());
//        form.setProductName(entity.getProductName());
//        form.setProductPrice(entity.getProductPrice());
//        form.setStock(entity.getProductStock());
        return null;
    }
}
