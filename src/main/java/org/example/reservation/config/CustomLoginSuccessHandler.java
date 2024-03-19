package org.example.reservation.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Set;

/**
 * カスタム認証ハンドラークラス
 */
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     *  カスタム認証メソッド
     *  権限種別に応じてデフォルトページの変更をする
     * @param request リクエスト
     * @param authentication 認証
     * @throws IOException 例外
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException{
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            System.out.println("logging-admin");
            response.sendRedirect(request.getContextPath() + "/admin/manage");
        } else {
            System.out.println("logging-else");
            response.sendRedirect(request.getContextPath() + "/takeout/top"); // デフォルトページ(top)
        }

    }
}
