package org.example.reservation.service.spec;


import java.util.List;

import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.projection.ReservationProductDto;
import org.example.reservation.form.ReservationForm;

public interface ReservationService 
{
	List<Reservation> getReservationAll(); //全件取得
	boolean setReservationRegister(ReservationForm inputReservation);//予約情報をデータベースに登録
	List<ReservationProductDto> getReservationProductDtoAll();
}
