package org.example.reservation.entity.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReservationProductDto {

    /**
     * 予約id/商品id/商品名/予約日時/予約者の名前/電話番号でしゅとく
     */
    private Long reservationId;
    private Long productId;
    private String productName;
    private LocalDateTime reservationTime;
    private String customerName;
    private String customerTel;
}
