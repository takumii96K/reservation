package org.example.reservation.service.spec;

import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.dto.ReservationDto;
import org.example.reservation.entity.dto.ReservationProductDto;
import org.example.reservation.entity.projection.ReservationProjection;
import org.example.reservation.form.ReservationInputForm;

import java.util.List;

public interface ReservationService {
	List<Reservation> getReservationAll(); //全件取得
	Reservation createReservation(ReservationInputForm inputReservation);
	List<ReservationProductDto> getReservationProductDtoAll();
	ReservationProjection getReservationProjectionById(Long id);
	void updateReservation(Reservation reservation);
	void updateReservation(ReservationDto dto);
}
