$(document).ready(function() {
    $('#checkoutButton').click(function() {
        const csrfToken = $('meta[name="_csrf"]').attr('content');
        const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');

        let cartItems = [];
        $('.cart-item').each(function() {
            const $this = $(this);
            let item = {
                itemId: $this.data('product-id'),
                itemName: $this.data('item-name'),
                price: $this.data('price'),
                quantity: $this.data('quantity')
            };
            cartItems.push(item);
        });

        $.ajax({
            url: '/cart/checkout',
            type: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            data: JSON.stringify({items: cartItems}),
            success: function(response) {
                alert('決済に進みます。');
                window.location.href = '/takeout/product/reservation';
            },
            error: function() {
                alert('処理が失敗しました。');
            }
        });
    });
});
