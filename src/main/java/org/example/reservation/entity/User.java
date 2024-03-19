package org.example.reservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reservation.entity.converter.UserAuthorityConverter;
import org.example.reservation.entity.enumeration.AuthorityKind;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name ="user_table")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails,Serializable{

    /* ユーザーID (PK)*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /* ユーザー名 */
    @NotBlank
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    /* パスワード */
    @NotBlank

    @Column(name ="user_password", nullable = false)
    private String password;

    @NotNull
    @Column(name ="user_tel",nullable = false)
    private String tel;

    /* ユーザー権限種別 */
    @Convert(converter = UserAuthorityConverter.class)
    @Column(name = "authority_kind")
    private AuthorityKind authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.authorities.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        // アカウントの有効期限に関するロジックをここに実装します。
        // デフォルトでtrueを返すか、フィールドをチェックします。
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // アカウントがロックされているかどうかに関するロジック
        return true;  // デフォルトでアカウントはロックされていない
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 資格情報の有効期限に関するロジック
        return true;  // デフォルトで資格情報は期限切れではない
    }

    @Override
    public boolean isEnabled() {
        // アカウントが有効かどうかに関するロジックを
        return true;  // デフォルトでアカウントを有効
    }
}