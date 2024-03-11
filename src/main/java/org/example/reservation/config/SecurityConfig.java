package org.example.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * SecurityConfig
 *
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                //リクエスト認可の設定
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER")
                        .anyRequest().permitAll())
                //ログイン画面の設定
                .formLogin(login -> login
                        .loginPage("/login") //ログイン画面のパス
                        .successHandler(customLoginSuccessHandler())
                        .failureUrl("/login?failure"))//ログイン失敗時の画面憑依 リクエストパラメーターfailureを設定(Controllerでメッセージを設定)
                //認可失敗時のエラー画面
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/display-access-denind"))
                //ログアウトの設定
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    /**
     * 認証カスタムハンドラー
     * @return カスタムサクセスハンドラークラスへ
     */
    @Bean
    public AuthenticationSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    /**
     * パスワードエンコーダー
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
