package org.example.reservation.repository;

import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.projection.ReservationProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface JpaReservationRepository extends JpaRepository<Reservation, Long>{

    @Query("SELECT new org.example.reservation.entity.projection.ReservationProductDto" +
            "(r.reservationId, p.productId, p.productName, r.dateTime, r.customerName, r.tel) " +
            "FROM Reservation r " +
            "JOIN r.products p")
    List<ReservationProductDto> findReservationDetails();
}
