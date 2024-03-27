package org.example.reservation.config;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class UserDetailsIml implements UserDetails {
    private final User user;

    public User getUser() {return user;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getAuthorities().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
