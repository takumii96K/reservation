package org.example.reservation.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.form.ProductForm;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.example.reservation.service.spec.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ManageController {
    /** DI対象 */
    private final ProductService productService;
    private final UserService userService;
    private final ReservationService reservationService;
    private final ProductDtoConverter converter;

    // 商品情報の編集フォーム表示フラグ
    @Getter
    private boolean productFormVisible = false;

    // 商品情報の編集フォーム表示フラグのリセット
    private void resetProductFormVisible() {
        productFormVisible = false;
    }


    /**
     * 商品情報,ユーザー情報,予約情報の一覧を表示
     * @param model products:ProductDto, users, reservation
     * @return view: manage.html
     */
    @GetMapping("/manage")
    public String showList(Model model) {
        //商品情報
        model.addAttribute("products" ,converter.convertToDtoList(productService.getAllProducts()));
        // 商品情報の編集フォーム表示フラグ
        model.addAttribute("productFormVisible", this.productFormVisible);

        model.addAttribute("form", new ProductForm());
        //authorityKindが1のユーザーのみを取得
        model.addAttribute("users", userService.findUserWithAuthorityKindOne());
        //予約情報
        model.addAttribute("reservations",reservationService.getReservationProductDtoAll());
        return "/manage";
    }

    /** 商品情報の編集処理 */
//    @RequestMapping("/edit-product/{productId}")
//    public String editProduct(@PathVariable("productId") Long productId, @ModelAttribute ProductForm form,Model model) {
//            // 編集フォームを表示するためのフラグを設定
//            productFormVisible = true;
//
//            // 編集処理を実装
//            Product product = converter.convertToEntity(form);
//            productService.updateProduct(productId, editedProduct);
//
//            // 商品情報の編集が完了したのでフラグをリセット
//            resetProductFormVisible();
//
//            // 編集された商品情報をリクエスト属性に追加
//            model.addAttribute("editedProduct", editedProductForm);
//
//            return "redirect:/takeout/manage"; // 商品情報の一覧ページにリダイレクト
//
//    }

        /** 商品情報の追加処理 */
        @PostMapping("/add-product")
        public String addProduct(@ModelAttribute("form") ProductForm form) {
            productService.createProduct(form);
            return "redirect:/manage";
        }

}