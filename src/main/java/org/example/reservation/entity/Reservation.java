package org.example.reservation.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reservation.entity.converter.ReservationStatusConverter;
import org.example.reservation.entity.enumeration.ReservationStatus;

@Entity
@Data
@Table(name="reservation")
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private long reservationId;
	
	//@NotNull
	@Column(name="reservation_time")
	private LocalDateTime dateTime;	//予約日時
	
	@NotNull
	@Column(name="customer_name")
	private String customerName;     //予約者の名前
	
	@NotNull
	@Column(name="customer_tel")
	private String tel;				//電話番号
	
	//@NotNull
	@Column(name="reservation_email")
	private String email;			//メアド

	@OneToMany(mappedBy = "reservation")
	private List<Order> orders;

	@Column(name = "reservation_status")
	@Convert(converter = ReservationStatusConverter.class)
	private ReservationStatus status = ReservationStatus.UNCONFIRMED;  // 予約の状態（"未確定", "確定", "キャンセル" など）

	// ユーザー情報はオプショナルとします
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
