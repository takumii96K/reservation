package org.example.reservation.entity.projection;

import java.math.BigDecimal;

public interface ProductProjection {
    Long getProductId();
    String getProductName();
    BigDecimal getProductPrice();
    int getProductStock();
}
