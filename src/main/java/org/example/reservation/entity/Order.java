package org.example.reservation.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="reservation_product")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "reservation_id" )
    private Reservation reservation;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "purchased_quantity")
    private int quantity;  // 購入された数量

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + (product != null ? product.getProductId() : "null") +
                ", reservationId=" + (reservation != null ? reservation.getReservationId() : "null") +
                ", quantity=" + quantity +
                '}';
    }

}