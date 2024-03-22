package org.example.reservation.entity.projection;

import org.example.reservation.entity.enumeration.ReservationStatus;

import java.time.LocalDateTime;
public interface ReservationProjection {
    Long getReservationId();
    LocalDateTime getDateTime();
    String getCustomerName();
    String getTel();
    String getEmail();
    ReservationStatus getStatus();
}
