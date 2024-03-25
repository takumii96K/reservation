$(document).ready(function() {
    $('#checkoutButton').click(function() {
        const csrfToken = $('meta[name="_csrf"]').attr('content');
        const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');

        $.ajax({
            url: '/cart/checkout',
            type: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            success: function(response) {
                alert('決済はまだ完了していません。');
                window.location.href = response.redirectUrl;
            },
            error: function(xhr) {
                // エラーメッセージをresponseから取得して表示
                let errorMessage = xhr.responseJSON && xhr.responseJSON.message ?
                    xhr.responseJSON.message :
                    '処理が失敗しました。';
                alert(errorMessage);
                // 任意：エラーが「カートに商品を追加してください」の場合、商品選択画面にリダイレクトする
                if (errorMessage === "商品をカートに入れてください") {
                    window.location.href = '/takeout/product'; // 適切な商品選択画面のURLに置き換えてください
                }
            }
        });
    });
});
