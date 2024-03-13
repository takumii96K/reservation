package org.example.reservation.entity.converter;

import org.example.reservation.entity.Product;
import org.example.reservation.form.ProductForm;

import java.util.ArrayList;
import java.util.List;

public class ProductFormConverter implements Converter<ProductForm, Product>{
    @Override
    public Product convertToEntity(ProductForm form) {
        return null;
    }

    @Override
    public ProductForm convertToDto(Product entity) {
        ProductForm form = new ProductForm();
        form.setId(entity.getProductId());
        form.setProductName(entity.getProductName());
//        form.setProductPrice(entity.getProductPrice());
//        form.setStock(entity.getProductStock());
        return null;
    }

    //    public LIst<ProductForm> convertToForm(List<Product> allEntity) {
//        ProductForm form = new ProductForm();
//        form.setId(entity.getProductId());
//        form.setProductName(entity.getProductName());
//        form.setProductPrice(entity.getProductPrice());
//        form.setStock(entity.getProductStock());
//        return null;
//    }
    public List<ProductForm> convertToForm(List<Product> allEntity) {
        List<ProductForm> productForms = new ArrayList<>();

        for (Product entity : allEntity) {
            ProductForm form = new ProductForm();
            form.setId(entity.getProductId());
            form.setProductName(entity.getProductName());
//            form.setProductPrice(entity.getProductPrice());
            form.setProductStock(entity.getProductStock());
            productForms.add(form);
        }

        return productForms;
    }
}
