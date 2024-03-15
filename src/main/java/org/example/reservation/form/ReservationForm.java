package org.example.reservation.form;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationForm {
	
	//商品ID
private Integer id;

//↓の各変数(name,phone,email,date)にはreservation.htmlから貰ってきた値を格納される
@NotBlank//文字列が空かどうか確認する
@NotNull
private String name;

@NotBlank
@Length(min=10, max=10)
private String phone;

@NotBlank
@Email
private String email;

@NotBlank
@Past//過去の日付かどうか確認する
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
private LocalDateTime date;


}