package org.example.reservation.service.implement;

import java.math.BigDecimal;
import java.util.Map;

import org.example.reservation.entity.converter.IntoCartConverter;
import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.entity.projection.ProductProjection;
import org.example.reservation.repository.JpaOrderRepository;
import org.example.reservation.repository.JpaProductRepository;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartItem;
import org.example.reservation.session.CartSession;
import org.example.reservation.session.CheckoutRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartSession cartSession;
    private final JpaProductRepository productRepository;
    private final IntoCartConverter cartConverter;
    private final ProductDtoConverter productConverter;
    private final JpaOrderRepository orderRepository;



    /**
     * 商品をカートに追加
     * @param id item.id(product.id)
     * @param quantity item.quantity
     */
    @Override
    public void addItemToCart(Long id, int quantity) {
        ProductProjection record = productRepository.findProductProjectionByProductId(id);
        CartItem item = cartConverter.convertToDto(record);
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
    public void checkout(CheckoutRequest request) {

    }


	@Override
	public void submitShopping(CheckoutRequest request) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
//
//    @Override
//    public void submitShopping(CheckoutRequest request) {
//        // リクエスト内の各カートアイテムについて処理を行う //dtoのid-在庫, 購入個数をorderに登録する
//        request.getItems().forEach(cartItem -> {
//            //新規オーダーidの発行
//            Order order = new Order();
//            // データベースから対応する商品エンティティ(必要な情報のみ）を取得
//            ProductDto dto = productRepository.findProductByProductId(cartItem.getItemId());
//            // 商品の情報を更新
//            dto.setStock(dto.getStock() - cartItem.getQuantity()); //在庫更新処理
//
//            Product product = productConverter.convertToEntity(dto);
//            order.setProduct(product); //orderに商品を登録
//            order.setQuantity(cartItem.getQuantity());
////            order.setReservation();
//        });

//        // セッションから予約情報を取得
//        Reservation reservation = (Reservation) session.getAttribute("reservation");
//
//        if (reservation == null) {
//            // セッションに予約情報がない場合はエラー
//            throw new IllegalStateException("Reservation information is not found in session.");
//        }
//
//        // ショッピングカート内の各商品に対して処理を行う
//        for (CartItemDto item : cartDto.getItems()) {
//            Product product = productRepository.findById(item.getProductId())
//                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + item.getProductId()));
//
//            Order order = new Order();
//            order.setReservation(reservation);
//            order.setProduct(product);
//            order.setQuantity(item.getQuantity());
//
//            orderRepository.save(order);
//
//            // 商品の在庫を更新
//            product.setStock(product.getStock() - item.getQuantity());
//            productRepository.save(product);
//        }

//        // 予約情報を更新して保存
//        reservationRepository.save(reservation);
//    }
//
//    }


}
