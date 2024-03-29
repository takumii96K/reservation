package org.example.reservation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.reservation.entity.converter.CategoryConverter;
import org.example.reservation.entity.enumeration.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;        //商品ID

	@NotNull
	@Column(name = "product_name")
	private String productName;    //商品名

	@NotNull
	@Column(name = "product_price")
	private BigDecimal productPrice;    //商品の金額

	@NotNull
	@Column(name = "product_stock")
	private int stock;    //在庫数

	@JsonManagedReference
	@OneToMany(mappedBy = "product")
	private List<Order> orders = new ArrayList<>();

	@Convert(converter = CategoryConverter.class)
	@Column(name = "category")
	private Category category; // カテゴリフィールドを追加

	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "image_id")
	private Image image;

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", productPrice=" + productPrice +
				", stock=" + stock +
				'}';
	}
}

