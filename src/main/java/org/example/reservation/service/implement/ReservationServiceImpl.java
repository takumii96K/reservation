package org.example.reservation.service.implement;

import java.util.List;

import org.example.reservation.entity.Order;
import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.dto.ReservationDto;
import org.example.reservation.entity.dto.ReservationProductDto;
import org.example.reservation.entity.projection.ReservationProjection;
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
	 * 新規予約確定
	 * @param form ReservationInputForm
	 * @return boolean true/false
	 */
	@Override
	public Reservation createTemporaryReservation(ReservationInputForm form){
		Reservation reservation = new Reservation();
			reservation.setCustomerName(form.getName());
			reservation.setTel(form.getPhone());
			reservation.setEmail(form.getEmail());
			reservation.setDateTime(form.getDate());
		return reservation;
	}

	@Override
	public Reservation savedReservation(Reservation reservation, List<Order> orders) {
		reservation.setOrders(orders);
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

	@Override
	public ReservationProjection getReservationProjectionById(Long id) {
		return repository.findReservationProjectionByReservationId(id);
	}

	@Override
	public void updateReservation(Reservation reservation) {
		repository.save(reservation);
	}

	@Override
	public void updateReservation(ReservationDto dto) {

	}
}
