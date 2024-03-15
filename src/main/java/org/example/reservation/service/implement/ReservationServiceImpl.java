package org.example.reservation.service.implement;

import java.util.List;

import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.projection.ReservationProductDto;
import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.repository.JpaReservationRepository;
import org.example.reservation.service.spec.ReservationService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final JpaReservationRepository repository;

	@Override
	public List<Reservation> getReservationAll()
	{	
		return repository.findAll();
	}

	/**
	 * 予約情報登録
	 * @param form ReservationInputForm
	 * @return boolean true/false
	 */
	@Override
	public boolean registerReservation(ReservationInputForm form){
		Reservation reservation = new Reservation();
			reservation.setCustomerName(form.getName());
			reservation.setTel(form.getPhone());
			reservation.setEmail(form.getEmail());
			reservation.setDateTime(form.getDate());
			
		repository.save(reservation);
		return true;
	}

	/**
	 * 管理画面：予約履歴の取得
	 * @return List<reservationProductDto>
	 */
	@Override
	public List<ReservationProductDto> getReservationProductDtoAll() {
		return repository.findReservationDetails();
	}
}
