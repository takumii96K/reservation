package org.example.reservation.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String imageUrl;   //商品画像

	@NotNull
	@Column(name = "product_price")
	private BigDecimal productPrice;	//商品の金額

	@NotNull
	@Column(name = "product_stock")
	private int productStock;	//在庫数

	@ManyToMany(mappedBy = "products")
	private List<Reservation> reservations;
}
