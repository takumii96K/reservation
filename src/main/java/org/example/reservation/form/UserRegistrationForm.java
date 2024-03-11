package org.example.reservation.form;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class UserRegistrationForm {
    private String name;
    private String password;
    private String tel;
}
