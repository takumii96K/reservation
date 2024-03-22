package org.example.reservation.service.spec;


import java.util.List;

import org.example.reservation.entity.Order;
import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.dto.ReservationDto;
import org.example.reservation.entity.dto.ReservationProductDto;
import org.example.reservation.entity.projection.ReservationProjection;
import org.example.reservation.form.ReservationInputForm;

public interface ReservationService 
{
	List<Reservation> getReservationAll(); //全件取得
	Reservation createTemporaryReservation(ReservationInputForm inputReservation);
	Reservation savedReservation(Reservation reservation, List<Order> orders);
	List<ReservationProductDto> getReservationProductDtoAll();
	ReservationProjection getReservationProjectionById(Long id);
	void updateReservation(Reservation reservation);
	void updateReservation(ReservationDto dto);
}
