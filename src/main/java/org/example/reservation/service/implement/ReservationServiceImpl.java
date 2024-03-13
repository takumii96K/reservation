package org.example.reservation.service.implement;

import java.util.List;
import org.example.reservation.entity.Reservation;
import org.example.reservation.form.ReservationForm;
import org.example.reservation.repository.JpaReservationRepository;
import org.example.reservation.service.spec.ReservationService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final JpaReservationRepository repository;

	@Override
	public List<Reservation> getReservationAll() {
		
		return repository.findAll();
	}

	@Override
	public void setReservationRegister(ReservationForm inputForm) {
		//データベースに登録する処理
		Reservation reservation = new Reservation();

		reservation.setCustomerName(inputForm.getName());
		reservation.setTel(inputForm.getPhone());
		repository.save(reservation);
	}

}
