package org.example.reservation.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 予約id/商品id/商品名/予約日時/予約者の名前/電話番号を取得するためのDTOクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationProductDto {
    private Long reservationId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private LocalDateTime reservationTime;
    private String customerName;
    private String customerTel;
    private String email;
}
