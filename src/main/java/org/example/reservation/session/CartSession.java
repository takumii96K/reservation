package org.example.reservation.session;

import lombok.Data;
import org.example.reservation.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class CartSession implements Serializable {

    private Cart cart;

    public void resetCart() {
        this.cart = new Cart(); // 新しい空のカートを設定
    }

    public Cart getCart() {
        if (this.cart == null) {
            this.cart = new Cart();
        }
        return this.cart;
    }

}
