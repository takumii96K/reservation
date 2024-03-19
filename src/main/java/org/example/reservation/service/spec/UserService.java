package org.example.reservation.service.spec;


import java.util.List;

import org.example.reservation.entity.User;
import org.example.reservation.form.UserRegistrationForm;

public interface UserService {

    void registerUser(UserRegistrationForm form);

    void deleteUser(Long id);

    void updateUser(UserRegistrationForm form);

    List<User> findUserWithAuthorityKindOne();
}
