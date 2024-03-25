$(document).ready(function() {
    $('.confirm-update').click(function() {
        // 'this' refers to the clicked '.confirm-update' button.
        const tr = $(this).closest("tr"); // Using jQuery's closest method
        const productId = tr.data('productId'); // Using jQuery's data method
        const newQuantity = tr.find(".new-quantity").val(); // Using jQuery to find and get value
        const price = tr.data('price'); // Using jQuery's data method to get price

        const csrfToken = $('meta[name="_csrf"]').attr('content');
        const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');

        $.ajax({
            url: '/cart/update',
            type: 'POST',
            contentType: 'application/json',
            headers: {
                [csrfHeaderName]: csrfToken
            },
            data: JSON.stringify({
                itemId: productId,
                quantity: newQuantity
            }),
            success: function () {
                // Update the quantity and subtotal in the table row
                tr.find(".quantity").text(newQuantity); // Updating text of .quantity within the tr
                tr.find(".subtotal").text(newQuantity * price); // Calculating and updating the subtotal
                displayMessage('商品の数量を更新しました', 'success');
            },
            error: function () {
                displayMessage('数量の更新に失敗しました', 'error');
            }
        });
    });
});
