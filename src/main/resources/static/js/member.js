document.addEventListener('DOMContentLoaded', function() {
    const profiles = document.querySelectorAll('.profile');

    // スクロール時のイベントリスナーを追加
    window.addEventListener('scroll', function() {
        profiles.forEach((profile, index) => {
            // プロフィールが画面内に入ったかどうかを判定
            const rect = profile.getBoundingClientRect();
            const windowHeight = window.innerHeight || document.documentElement.clientHeight;
            const isVisible = rect.top < windowHeight;

            // プロフィールが画面内に入ったらアニメーションを適用
            if (isVisible) {
                setTimeout(() => {
                    profile.style.opacity = '1';
                    profile.style.transform = 'translateY(0)';
                }, index * 200); // 適切な遅延を設定
            }
        });
    });
});