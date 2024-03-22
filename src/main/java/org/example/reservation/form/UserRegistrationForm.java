package org.example.reservation.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter @Setter
@NoArgsConstructor
public class UserRegistrationForm {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String tel;
}
