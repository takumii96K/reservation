package org.example.reservation.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
public class UserRegistrationForm {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    @Pattern(regexp="\\d{10}", message="電話番号は10桁の数字でなければなりません")
    private String tel;
}
