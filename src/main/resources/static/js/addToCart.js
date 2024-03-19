function addToCart(element) {
    let productId = element.closest('.product-details').querySelector('input[name="productId"]').value;
    let quantity = element.closest('.product-details').querySelector('.quantity-input').value;
    let csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    let csrfHeaderName = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    $.ajax({
        url: '/cart/add',
        type: 'POST',
        contentType: 'application/json',
        headers: {
            'X-CSRF-TOKEN': csrfToken
        },
        data: JSON.stringify({
            productId: productId,
            quantity: quantity
        }),
        success: function() {
            displayMessage('商品がカートに追加されました!', 'success');
        },
        error: function() {
            displayMessage('商品をカートに追加する際にエラーが発生しました。もう一度お試しください。', 'error');
        }
    });
}

function displayMessage(text, type) {
    let messageContainer = document.getElementById('message-container');
    let message = document.getElementById('message');
    messageContainer.style.display = 'block';
    messageContainer.style.backgroundColor = type === 'success' ? '#4CAF50' : '#f44336';
    message.textContent = text;
    setTimeout(function() {
        messageContainer.style.display = 'none';
    }, 3000);
}