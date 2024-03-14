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
	public boolean setReservationRegister(ReservationForm rvForm)
	{
		//データベース(reservationテーブル)呼び出し
		Reservation reservation = new Reservation();
		
		//予約画面で受け取った値(name,phone,email,date)が全てnullじゃなかった場合true
		if(rvForm.getName() != null&& !rvForm.getName().isEmpty() && rvForm.getPhone() != null 
			&& rvForm.getEmail() != null && rvForm.getDate() != null)
		{
			//set処理
			reservation.setCustomerName(rvForm.getName());//名前
			reservation.setTel(rvForm.getPhone());		//電話番号
			reservation.setEmail(rvForm.getEmail());   	//メアド
			reservation.setDateTime(rvForm.getDate());	//日時
			
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
