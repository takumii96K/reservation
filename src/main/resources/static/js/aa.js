$(document).ready(function() {
    // 決済ボタンのクリックイベント
    $('#checkoutButton').click(function() {
        const csrfToken = $('meta[name="_csrf"]').attr('content');
        const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');

        let cartItems = $('.cart-item').map(function() {
            return {
                itemId: $(this).data('product-id'),
                itemName: $(this).data('item-name'),
                price: $(this).data('price'),
                quantity: $(this).data('quantity')
            };
        }).get();

        $.ajax({
            url: '/cart/checkout',
            type: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            data: JSON.stringify({ items: cartItems }),
            success: function() {
                alert('決済が完了しました。');
                window.location.href = '/takeout/top';
            },
            error: function() {
                alert('決済処理に失敗しました。');
            }
        });
    });

    // 商品の更新ボタンのクリックイベント
    $('.update-item').click(function() {
        let itemId = $(this).data('item-id');
        let newQuantity = $(this).closest('tr').find('.quantity-input').val();
        updateCartItem(itemId, newQuantity);
    });

    // 商品の削除ボタンのクリックイベント
    $('.delete-item').click(function() {
        let itemId = $(this).data('item-id');
        deleteCartItem(itemId);
    });
});

function updateCartItem(itemId, quantity) {
    const csrfToken = $('meta[name="_csrf"]').attr('content');
    const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');
    $.ajax({
        url: '/cart/update',
        type: 'POST',
        contentType: 'application/json',
        headers: {
            [csrfHeaderName]: csrfToken
        },
        data: JSON.stringify({ itemId: itemId, quantity: quantity }),
        success: function(response) {
            location.reload();
        },
        error: function() {
            alert('商品の更新に失敗しました。');
        }
    });
}

function deleteCartItem(itemId) {
    const csrfToken = $('meta[name="_csrf"]').attr('content');
    const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');
    $.ajax({
        url: '/cart/delete',
        type: 'POST',
        contentType: 'application/json',
        headers: {
            [csrfHeaderName]: csrfToken
        },
        data: JSON.stringify({ itemId: itemId }),
        success: function(response) {
            location.reload();
        },
        error: function() {
            alert('商品の削除に失敗しました。');
        }
    });
}
