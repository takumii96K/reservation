package org.example.reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.reservation.entity.converter.ReservationStatusConverter;
import org.example.reservation.entity.enumeration.ReservationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="reservation")
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

	@JsonManagedReference
	@OneToMany(mappedBy = "reservation")
	private List<Order> orders = new ArrayList<>();

	@Column(name = "reservation_status")
	@Convert(converter = ReservationStatusConverter.class)
	private ReservationStatus status = ReservationStatus.UNCONFIRMED;  // 予約の状態（"未確定", "確定", "キャンセル" など）

	// ユーザー情報はオプショナル
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		return "Reservation{" +
				"reservationId=" + reservationId +
				", dateTime=" + dateTime +
				", customerName='" + customerName + '\'' +
				", tel='" + tel + '\'' +
				", email='" + email + '\'' +
				", status=" + status +
				", userId=" + (user != null ? user.getUserId() : "null") +
				'}';
	}
}
