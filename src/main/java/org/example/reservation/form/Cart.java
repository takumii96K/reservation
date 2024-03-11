package org.example.reservation.form;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Cart {
    // カートから商品を取得
    private final List<CartItem> items = new ArrayList<>();

    // カートに商品を追加
    public void addItem(CartItem item) {
        items.add(item);
    }

    // カートの全アイテムの価格の合計を計算
    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getItemCount() {
        return items.size();
    }
}
