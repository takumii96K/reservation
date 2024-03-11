package org.example.reservation.entity.converter;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.User;
import org.example.reservation.entity.enumeration.AuthorityKind;
import org.example.reservation.form.UserRegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRegistrationFormConverter implements Converter<UserRegistrationForm, User>{

    private final PasswordEncoder encoder;
    public User convertToEntity(UserRegistrationForm form){
        User user = new User();
        user.setUserName(form.getName());
        user.setPassword(encoder.encode(form.getPassword()));
        user.setTel(form.getTel());
        //新規ユーザーのため権限はデフォルトでROLE＿USER
        user.setAuthorities(AuthorityKind.ROLE_USER);
        return user;
    }
    public UserRegistrationForm convertToForm(User user){
        UserRegistrationForm form = new UserRegistrationForm();
        form.setName(user.getUserName());
        form.setTel(user.getTel());
        //パスワードは取り出すことはない
        return form;
    }
}
