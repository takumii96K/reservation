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
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String tel;
}
