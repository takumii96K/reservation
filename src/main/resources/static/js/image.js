// // $(document).ready(function() {
// //     $('.product').each(function(index, product) {
// //         let productId = $(product).find('.product-details').data('product-id');
// //
// //         const csrfToken = $('meta[name="_csrf"]').attr('content');
// //         const csrfHeaderName = $('meta[name="_csrf_header"]').attr('content');
// //
// //         $.ajax({
// //             url: '/images/' + productId +'.jpg',
// //             type: 'GET',
// //             contentType: 'application/json',
// //             headers: {
// //                 [csrfHeaderName]: csrfToken
// //             },
// //             success: function(response) {
// //                 $('#product-image-' + productId).html(
// //                     $('<img>').attr('src', response.url)
// //                 );}
// //         });
// //     });
// // });
// $(document).ready(function() {
//     $('.product').each(function(index, product) {
//         let productId = $(product).find('.product-details').data('product-id');
//         // 画像URLを直接<img>タグのsrc属性にセット
//         $('#product-image-' + productId).html(
//             $('<img>').attr('src', '/images/' + productId + '.jpg')
//         );
//     });
// });