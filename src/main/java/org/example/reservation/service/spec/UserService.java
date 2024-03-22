package org.example.reservation.service.spec;


import java.util.List;

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
    
//    //全ユーザー取得
//    List<User> getAllUser();
    
    //authorityKindが1(ROLE_USER)のユーザーのみを取得
    List<User> getPeopleWithAuthorityKindOne();
 

}
