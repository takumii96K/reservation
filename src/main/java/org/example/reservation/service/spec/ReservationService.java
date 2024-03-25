package org.example.reservation.service.spec;

import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.dto.ReservationProductDto;
import org.example.reservation.form.ReservationInputForm;

import java.util.List;

public interface ReservationService {
	Reservation createReservation(ReservationInputForm inputReservation);
	List<ReservationProductDto> getReservationProductDtoAll();
}
