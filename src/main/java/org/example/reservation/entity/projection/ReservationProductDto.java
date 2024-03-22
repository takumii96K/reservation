package org.example.reservation.entity.projection;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationProductDto {

	/**
	 * 予約id/商品id/商品名/予約日時/予約者の名前/電話番号/emailでしゅとく
	 */
	private Long reservationId;
	private Long productId;
	private String productName;
	private LocalDateTime reservationTime;
	private String customerName;
	private String customerTel;
	private String email;



}
