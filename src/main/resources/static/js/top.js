// ページの読み込みが完了した時に実行される関数
window.onload = function() {
    // ページのロードが完了したら、animate-onloadクラスを持つbody要素にアニメーションをトリガーするためのクラスを追加
    document.body.classList.add('animate-onload');
};


/**
 informationのアニメーション
 */
// スクロール時にアニメーションを実行する関数
function animateOnScroll() {
    let informationElements = document.querySelectorAll('.information');
    let accordionElements = document.querySelectorAll('.accordion-003');

    informationElements.forEach(function(element) {
        // 要素が画面内に表示されたらアニメーションを実行
        if (isElementInViewport(element)) {
            element.classList.add('show');
        }
    });

    accordionElements.forEach(function(element) {
        // 要素が画面内に表示されたらアニメーションを実行
        if (isElementInViewport(element)) {
            element.classList.add('show');
        }
    });
}

// 指定された要素が画面内に表示されているかどうかを判定する関数
function isElementInViewport(el) {
    let rect = el.getBoundingClientRect();
    return (
        rect.top >= 0 &&
        rect.bottom <= (window.innerHeight || document.documentElement.clientHeight)
    );
}

// スクロール時にアニメーションを実行
window.addEventListener('scroll', animateOnScroll);

// ページ読み込み時にもアニメーションを実行
window.addEventListener('load', animateOnScroll);


/**
 広告のアニメーション
 */
// ページ読み込み時にポップアップを表示する関数
function openPopup() {
    document.getElementById("popupOverlay").style.display = "flex";
}

// ポップアップを閉じる関数
function closePopup() {
    document.getElementById("popupOverlay").style.display = "none";
}

// 画面遷移後にポップアップを表示するための関数
function showPopupAfterDelay() {
    setTimeout(openPopup, 2000); // 2秒後にポップアップを表示
}

// ページ読み込み時に関数を実行
window.onload = function() {
    showPopupAfterDelay();
};

// スクロールまたはページの読み込み時に実行される関数
window.addEventListener('scroll', popInImages);
window.addEventListener('DOMContentLoaded', popInImages);

function popInImages() {
    const images = document.querySelectorAll('.ad-container img');

    images.forEach((image) => {
        // 画像の上端がウィンドウの下端よりも上に位置する場合に、popクラスを追加してアニメーションをトリガー
        if (image.getBoundingClientRect().top < window.innerHeight) {
            image.classList.add('pop');
        }
    });
}