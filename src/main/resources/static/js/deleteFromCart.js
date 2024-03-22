function deleteFromCart(button) {
    const tr = button.closest("tr");
    const productId = tr.dataset.productId;

    const csrfToken = $('meta[name="_csrf"]').attr('content');
    const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');

    $.ajax({
        url: '/cart/delete',
        type: 'POST',
        contentType: 'application/json',
        headers: {
            [csrfHeaderName]: csrfToken
        },
        data: JSON.stringify({ itemId: productId }),
        success: function() {
            tr.remove(); // 商品行をページから削除
            displayMessage('商品をカートから削除しました', 'success');
            // 任意: カート内の商品合計などの更新処理をここに追加
        },
        error: function() {
            displayMessage('商品の削除に失敗しました', 'error');
        }
    });
}