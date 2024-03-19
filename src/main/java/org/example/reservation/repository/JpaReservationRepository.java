package org.example.reservation.repository;

import java.util.List;

import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.projection.ReservationProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReservationRepository extends JpaRepository<Reservation, Long>{

    @Query("SELECT new org.example.reservation.entity.projection.ReservationProductDto" +
            "(rp.reservation.reservationId, rp.product.productId, rp.product.productName, rp.quantity, " +
             "rp.reservation.dateTime, rp.reservation.customerName, rp.reservation.tel, rp.reservation.email) " +
            "FROM Order rp")
    List<ReservationProductDto> findAllReservationsWithProducts();
}
