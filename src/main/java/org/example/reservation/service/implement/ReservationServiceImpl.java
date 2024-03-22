package org.example.reservation.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.dto.ReservationProductDto;
import org.example.reservation.form.ReservationInputForm;
import org.example.reservation.repository.JpaReservationRepository;
import org.example.reservation.service.spec.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final JpaReservationRepository repository;

	/**
	 * 新規予約確定
	 * @param form ReservationInputForm
	 * @return boolean true/false
	 */
	@Override
	public Reservation createReservation(ReservationInputForm form){
		Reservation reservation = new Reservation();
			reservation.setCustomerName(form.getName());
			reservation.setTel(form.getPhone());
			reservation.setEmail(form.getEmail());
			reservation.setDateTime(form.getDate());
		return repository.save(reservation);
	}


	/**
	 * 管理画面：予約履歴の取得
	 * @return List<reservationProductDto>
	 */
	@Override
	public List<ReservationProductDto> getReservationProductDtoAll() {
		return repository.findAllReservationsWithProducts();
	}
}
