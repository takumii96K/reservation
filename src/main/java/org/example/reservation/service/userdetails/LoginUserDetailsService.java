package org.example.reservation.service.userdetails;

import java.util.Collections;
import java.util.List;

import org.example.reservation.entity.converter.UserRegistrationFormConverter;
import org.example.reservation.entity.projection.UserLoginProjection;
import org.example.reservation.exception.DuplicateUserNameException;
import org.example.reservation.form.UserRegistrationForm;
import org.example.reservation.repository.JpaUserRepository;
import org.example.reservation.service.spec.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * UserDetailサービス
 */
@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService, UserService {

    private final JpaUserRepository repository;
    private final UserRegistrationFormConverter converter;


    /**
     * User情報をDBからロードする
     *
     * @param username the username identifying the user whose data is required.
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //ログイン情報をレコードに取得
        UserLoginProjection record = repository.findProjectionByUserName(username);

        //レコードの有無を確認
        if (record == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // ユーザーに割り当てられた権限でGrantedAuthorityリストを作成
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(record.getAuthorities().toString()));

        // UserDetailsを生成
        return new User(record.getUserName(), record.getPassword(), authorities);
    }


    /**
     * ユーザー登録
     * @param form UserRegistrationForm
     */
    @Override
    public void registerUser(UserRegistrationForm form) {

        //重複チェック
        if(repository.existsByUserName(form.getUserName())){
            throw new DuplicateUserNameException("ユーザーID " + form.getUserName() + " が重複しています");
        }
        //登録
        repository.save(converter.convertToEntity(form));

    }

    /**
     * ユーザー削除
     * @param id userId
     */
    @Override
    public void deleteUser(Long id) {repository.deleteById(id);}

    /**
     * ユーザー情報更新
     * @param form UserRegistrationForm
     */
    @Override
    public void updateUser(UserRegistrationForm form) {
        //重複チェック
        if(repository.existsByUserName(form.getUserName())){
            throw new DuplicateUserNameException("ユーザーID " + form.getUserName() + " が重複しています");
        }
        repository.save(converter.convertToEntity(form)); //false

        //登録
        repository.save(converter.convertToEntity(form));
    }

    @Override
    public List<org.example.reservation.entity.User> findUserWithAuthorityKindOne() {
        return repository.getUserWithAuthorityKindOne();
    }

}
