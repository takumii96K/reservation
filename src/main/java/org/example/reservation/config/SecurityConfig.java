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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * SecurityConfig
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
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cart/add").permitAll()
                        .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER")
                        .requestMatchers("/user/profile").authenticated()
                        .requestMatchers("/cart/checkout").permitAll()
                        .anyRequest().permitAll())
                //ログイン画面の設定
                .formLogin(login -> login
                        .loginPage("/login") //ログイン画面のパス
                        .successHandler(customLoginSuccessHandler())
                        .failureUrl("/login?failure"))//ログイン失敗時の画面憑依 リクエストパラメーターfailureを設定(Controllerでメッセージを設定)
                //認可失敗時のエラー画面
                .exceptionHandling(handling -> handling
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint()))
                //ログアウトの設定
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    /**
     * 認証カスタムハンドラー
     * @return AuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    /**
     * パスワードエンコーダー
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            // 認証済みユーザーが認可に失敗した場合
            response.sendRedirect("/login?error=accessDenied");
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            // 未認証ユーザーが保護されたリソースにアクセスした場合
            response.sendRedirect("/login?error=unauthorized");
        };
    }
}
