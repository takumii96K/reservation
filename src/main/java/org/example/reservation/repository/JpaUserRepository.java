package org.example.reservation.repository;

import java.util.List;

import org.example.reservation.entity.User;
import org.example.reservation.entity.projection.UserLoginProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {

    //usernameから初期管理者がログインするための情報のみを引き出す
    UserLoginProjection findProjectionByUserName(String username);

    //usernameはユニーク制約のためカスタムcheckメソッド
    boolean existsByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.authorities = org.example.reservation.entity.enumeration.AuthorityKind.ROLE_USER")
    List<User> getUserWithAuthorityKindOne();

}
