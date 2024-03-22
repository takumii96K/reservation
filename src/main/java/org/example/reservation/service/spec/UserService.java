package org.example.reservation.service.spec;


import java.util.List;

import org.example.reservation.entity.User;
import org.example.reservation.form.UserRegistrationForm;

public interface UserService {

    void registerUser(UserRegistrationForm form);

    void deleteUser(Long id);

    void updateUser(UserRegistrationForm form);

//    //全ユーザー取得
//    List<User> getAllUser();

    //authorityKindが1(ROLE_USER)のユーザーのみを取得
    List<User> getPeopleWithAuthorityKindOne();


    List<User> findUserWithAuthorityKindOne();
}
