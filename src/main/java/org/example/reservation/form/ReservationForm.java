package org.example.reservation.form;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

@NotBlank
@NotEmpty
private String name;

@NotBlank
@Length(min=10, max=10)
private String phone;

@NotBlank
@Email
private String email;

@NotBlank
@Past
private LocalDateTime date;


}