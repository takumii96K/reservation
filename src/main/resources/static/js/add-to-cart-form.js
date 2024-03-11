document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.add-to-cart-btn').forEach(function(button) {
        button.addEventListener('click', function() {
            const form = this.closest('form');
            const productId = form.querySelector('.product-id').value;
            const quantity = form.querySelector('.quantity').value;

            // CSRFトークンの値を安全に取得
            const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
            const csrfToken = csrfTokenMeta ? csrfTokenMeta.getAttribute('content') : '';

            // CSRFヘッダーの名前を安全に取得
            const csrfHeaderMeta = document.querySelector('meta[name="_csrf_header"]');
            const csrfHeader = csrfHeaderMeta ? csrfHeaderMeta.getAttribute('content') : '';

            fetch('/cart/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken  // CSRFトークンをヘッダーに追加
                },
                body: JSON.stringify({ productId: productId, quantity: quantity })
            }).then(response => {
                if (response.ok) {
                    return response.json(); // カートの状態を更新するレスポンスを受け取る
                }
                throw new Error('Network response was not ok.');
            }).then(data => {
                console.log('Product added:', data);
                updateCart(data); // カートのUIを更新
            }).catch(error => {
                console.error('Error adding product to cart:', error);
            });
        });
    });
});
function updateCart(cartData) {
    document.querySelector('#cart-item-count').textContent = cartData.itemCount; // カート内のアイテム数
    document.querySelector('#cart-total').textContent = cartData.totalPrice; // カート内の合計価格

    const cartItemsContainer = document.querySelector('#cart-items');
    cartItemsContainer.innerHTML = ''; // カート内のアイテムをクリア
    cartData.items.forEach(item => {
        const itemElement = document.createElement('div');
        itemElement.textContent = `${item.productName} x ${item.quantity} - ¥${item.price}`;
        cartItemsContainer.appendChild(itemElement);
    });
}