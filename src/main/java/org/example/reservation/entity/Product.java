package org.example.reservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name="product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;		//商品ID

	@NotNull
	@Column(name = "product_name")
	private String productName;	//商品名

	@Column(name = "product_url")
	private String imageUrl;

	@NotNull
	@Column(name = "product_price")
	private BigDecimal productPrice;	//商品の金額

	@NotNull
	@Column(name = "product_stock")
	private int stock;	//在庫数

	@OneToMany(mappedBy = "product")
	private List<Order> orders;
}
