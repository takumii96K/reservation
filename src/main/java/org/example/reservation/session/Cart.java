package org.example.reservation.session;

import lombok.Data;
import org.example.reservation.ResourceNotFoundException;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private final Map<Long, CartItem> items = new HashMap<>();

    public CartItem selectById(Long id){
        if(items.get(id) != null){
            return items.get(id);
        }else{
            throw new ResourceNotFoundException("カートにありません。");
        }
    }

    /**
     * カートに商品を追加する
     * @param item productからdtoへ変換したitem
     */
    public void addItem(CartItem item) {
        CartItem cartItem = items.get(item.getItemId());
        if (cartItem != null) {
            // 商品が既に存在する場合、数量を更新
            cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
            System.out.println("商品の数量を更新しました");

        } else {
            // 引数の新規商品と個数をカートitemsに登録
            items.put(item.getItemId(), item);
            System.out.println("商品をカートに追加しました");

        }
    }
    public void updateItem(Long id, int quantity){
        CartItem item = selectById(id);
        item.setQuantity(quantity);
    }
    /**
     * カートの商品を削除する
     * @param id item.id
     */
    public void removeItem(Long id) {
        items.remove(id);
    }

    /**
     * カートの中身を全て削除する
     */
    public void removeItemAll(){
        items.clear();
    }

    /**
     * カートの中身の合計金額を計算する
     * @return BigDecimal AllItemPrice
     */
    public BigDecimal calculateTotalAmount() {
        BigDecimal totalAmount = BigDecimal.ZERO; // 合計金額を0で初期化

        // カート内の各商品についてループ
        for (CartItem item : items.values()) {
            // 商品の合計価格（単価 * 数量）を計算し、合計金額に加算
            BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        }

        return totalAmount; // 計算された合計金額を返す
    }


}
