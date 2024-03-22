function addToCart(element) {
    let productId = element.closest('.product-details').querySelector('input[name="productId"]').value;
    let quantity = element.closest('.product-details').querySelector('.quantity-input').value;
    let productName = element.closest('.product-details').querySelector('.productName-span').textContent;
    let productPrice = element.closest('.product-details').querySelector('.productPrice-span').textContent;

    const csrfToken = $('meta[name="_csrf"]').attr('content');
    const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');
    $.ajax({
        url: '/cart/add',
        type: 'POST',
        contentType: 'application/json',
        headers: {
            [csrfHeaderName]: csrfToken
        },
        data: JSON.stringify({
            itemId: productId,
            itemName: productName,
            price: productPrice,
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