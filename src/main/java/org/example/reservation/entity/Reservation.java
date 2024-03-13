package org.example.reservation.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	

	
	@ManyToMany
	@JoinTable(
			name = "reservation_product",
			joinColumns = @JoinColumn(name = "reservation_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Product> products;
}
