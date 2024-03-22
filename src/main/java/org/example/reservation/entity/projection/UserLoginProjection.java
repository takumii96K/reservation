package org.example.reservation.entity.projection;

import org.example.reservation.entity.enumeration.AuthorityKind;

/**
 * 認証用 ユーザーログインプロジェクション
 */
public interface UserLoginProjection {
    String getUserName();
    String getPassword();
    AuthorityKind getAuthorities();

}