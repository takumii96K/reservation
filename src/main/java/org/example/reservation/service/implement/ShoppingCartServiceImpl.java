package org.example.reservation.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.converter.IntoCartConverter;
import org.example.reservation.entity.projection.ProductProjection;
import org.example.reservation.repository.JpaProductRepository;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.Cart;
import org.example.reservation.session.CartItem;
import org.example.reservation.session.CartSession;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartSession cartSession;
    private final JpaProductRepository repository;
    private final IntoCartConverter converter;

    /**
     * 商品をカートに追加
     * @param id item.id(product.id)
     * @param quantity item.quantity
     */
    @Override
    public void addItemToCart(Long id, int quantity) {
        ProductProjection record = repository.findProductProjectionByProductId(id);
        CartItem item = converter.convertToDto(record);
        item.setQuantity(quantity);

        //カートに指定idの商品と個数を追加 ※数量更新も含む
        cartSession.getCart().addItem(item);
    }


    /**
     * 商品をカートから削除
     * @param id item.id
     */
    @Override
    public void removeItemFromCart(Long id) {
        cartSession.getCart().removeItem(id);
    }

    /**
     * カートの合計を計算
     * @return BigDecimal
     */
    @Override
    public BigDecimal calculateTotalPrice() {
        return cartSession.getCart().calculateTotalAmount();
    }

    /**
     * カートの中身を削除(カートそのものは保持）
     */
    @Override
    public void refreshCart() {
        cartSession.resetCart();
    }

    @Override
    public Map<Long ,CartItem> lookInCart() {
        System.out.println("カートの中身を表示します");
        return cartSession.getCart().getItems();
    }

    @Override
    public void submitShopping() {
        Cart cart =cartSession.getCart();
    }


}
