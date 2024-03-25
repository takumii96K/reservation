package org.example.reservation.config;


import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.User;
import org.example.reservation.repository.JpaUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * DataInitializer
 * 起動時に実行
 *
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final JpaUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AdminProperties adminProperties; // プロパティファイルから読み込んだ管理者情報

    /**
     * 管理者プロパティの初期化
     * @param args args
     */
    @Override
    public void run(ApplicationArguments args) {
        // 管理者ユーザーを初期登録（admin:Properties参照）
        try {
            User admin = new User();
            admin.setUserName(adminProperties.getUsername());
            admin.setPassword(passwordEncoder.encode(adminProperties.getPassword()));
            admin.setTel(adminProperties.getTel());
            admin.setAuthorities(adminProperties.getAuthorities());

            // データベース内で同じユーザー名を持つユーザーが存在するか確認
            if (!repository.existsByUserName(admin.getUserName())) {
                repository.save(admin);
            } else {
                // 既存のユーザーが見つかった場合の処理
                System.out.println("Admin user already exists: " + admin.getUserName());
            }
        } catch (DataIntegrityViolationException e) {
            // DataIntegrityViolationExceptionをキャッチした場合の処理
            System.err.println("Data integrity violation while inserting admin user: " + e.getMessage());
            // ここで必要な任意の処理を追加
        } catch (Exception e) {
            // その他の例外をキャッチした場合の処理
            System.err.println("An error occurred while inserting admin user: " + e.getMessage());
        }
    }
}