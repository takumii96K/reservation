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
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name ="user_table")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

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

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}