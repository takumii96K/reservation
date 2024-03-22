package org.example.reservation.service.spec;


import org.example.reservation.entity.User;
import org.example.reservation.form.UserRegistrationForm;

import java.util.List;

public interface UserService {

    void registerUser(UserRegistrationForm form);

    void deleteUser(Long id);
    void updateUser(UserRegistrationForm form);

    List<User> findUserWithAuthorityKindOne();
}
