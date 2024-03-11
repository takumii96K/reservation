package org.example.reservation.config;

import lombok.Data;
import org.example.reservation.entity.enumeration.AuthorityKind;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 管理者プロパティDTO
 */
@Component
@ConfigurationProperties(prefix = "admin")
@Data
public class AdminProperties {
    private String username;
    private String password;
    private String tel;
    private AuthorityKind authorities;
}
