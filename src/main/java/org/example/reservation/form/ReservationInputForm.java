package org.example.reservation.form;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInputForm {
	
	//商品ID
private Integer id;

//↓の各変数(name,phone,email,date)にはreservation.htmlから貰ってきた値を格納される
@NotBlank//文字列が空かどうか確認する
@NotNull
private String name;

@NotBlank
@Pattern(regexp="\\d{10}", message="電話番号は10桁の数字でなければなりません")
private String phone;

@NotBlank
@Email
private String email;

@NotBlank
@Past//過去の日付かどうか確認する
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
private LocalDateTime date;


}