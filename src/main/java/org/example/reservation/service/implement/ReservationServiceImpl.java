package org.example.reservation.service.implement;

import java.util.List;

import org.example.reservation.entity.Reservation;
import org.example.reservation.entity.projection.ReservationProductDto;
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
	public List<Reservation> getReservationAll()
	{	
		return repository.findAll();
	}

	@Override
	public boolean setReservationRegister(ReservationForm inputForm)
	{
		//データベースに登録する処理
		Reservation reservation = new Reservation();
		
		//予約画面で受け取った値(name,phone,email,date)が全てnullじゃなかった場合true
		if(inputForm.getName() != null && inputForm.getPhone() != null
			&& inputForm.getEmail() != null)
		{
			//set処理
			reservation.setCustomerName(inputForm.getName());//名前
			reservation.setTel(inputForm.getPhone());		//電話番号
			reservation.setEmail(inputForm.getEmail());   	//メアド
			reservation.setDateTime(inputForm.getDate());	//日時
			
			repository.save(reservation);	//保存
			
			return true;
		}
		//受け取った値にnullがあった場合false
		else
		{
			return false;
		}
	}

	@Override
	public List<ReservationProductDto> getReservationProductDtoAll() {
		return repository.findReservationDetails();
	}

}
