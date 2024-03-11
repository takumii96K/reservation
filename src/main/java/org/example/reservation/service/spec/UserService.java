package org.example.reservation.service.spec;


import org.example.reservation.entity.User;
import org.example.reservation.form.UserRegistrationForm;

public interface UserService {

    /**
     * ユーザー登録
     * @param form UserRegistrationForm
     */
     void registerUser(UserRegistrationForm form);

    /**
     * ユーザー削除
     */
    void deleteUser(Long id);

    /**
     * 更新
     * @param form
     */
    void updateUser(UserRegistrationForm form);
}
