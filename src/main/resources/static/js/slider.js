// スライドショーの自動再生
$('.slider').slick({
    autoplay: true,//自動的に動き出すか。初期値はfalse。
    infinite: true,//スライドをループさせるかどうか。初期値はtrue。
    speed: 300,//スライドのスピード。初期値は300。
    slidesToShow: 3,//スライドを画面に3枚見せる
    slidesToScroll: 1,//1回のスクロールで1枚の写真を移動して見せる
    prevArrow: '<div class="slick-prev"></div>',//矢印部分PreviewのHTMLを変更
    nextArrow: '<div class="slick-next"></div>',//矢印部分NextのHTMLを変更
    centerMode: true,//要素を中央ぞろえにする
    variableWidth: true,//幅の違う画像の高さを揃えて表示
    dots: true,//下部ドットナビゲーションの表示
});

function plusSlides(n) {
    $('.slideshow-container').slick('slickGoTo', n);
}

// カードフェードインアニメーション
window.addEventListener('scroll', function() {
    let productCards = document.querySelectorAll('.product-card');
    let windowHeight = window.innerHeight;

    productCards.forEach(function(card, index) {
        let cardTop = card.getBoundingClientRect().top;
        let delay = index * 0.5; // 大きな遅延を設定
        if (cardTop < windowHeight) {
            setTimeout(function() {
                card.style.animation = 'fadeInUp 0.5s ease forwards'; // アニメーションを適用
            }, delay * 1000); // ミリ秒単位に変換して適用
        }
    });
});