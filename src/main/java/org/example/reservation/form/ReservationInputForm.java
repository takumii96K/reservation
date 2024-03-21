package org.example.reservation.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInputForm {

//↓の各変数(name,phone,email,date)にはreservation.htmlから貰ってきた値が格納される
@NotBlank//文字列が空かどうか確認する
@NotNull
private String name;

@NotBlank
private String phone;

@NotBlank
@Email
private String email;

@NotNull
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
private LocalDateTime date;


}