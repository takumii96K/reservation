package org.example.reservation.controller;

import org.example.reservation.entity.Product;
import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.form.ProductForm;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.example.reservation.service.spec.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ManageController {
    /** DI対象 */
    private final ProductService productService;
    private final UserService userService;
    private final ReservationService reservationService;
    private final ProductDtoConverter converter;

    // 商品情報の編集フォーム表示フラグ
    private boolean productFormVisible = false;

    // 商品情報の編集フォーム表示フラグのリセット
    private void resetProductFormVisible() {
        productFormVisible = false;
    }

    // 商品情報の編集フォーム表示フラグのゲッター
    public boolean isProductFormVisible() {
        return productFormVisible;
    }


    /** 商品情報,ユーザー情報,予約情報の一覧を表示 */
    @GetMapping("/manage")
    /**
     * 商品情報,ユーザー情報,予約情報の一覧を表示
     * @param model products:ProductDto, users, reservation
     * @return view: manage.html
     */
    @GetMapping("/takeout/manage")
    public String showList(Model model) {
        //商品情報
        model.addAttribute("products" ,converter.convertToDtoList(productService.getAllProducts()));
        // 商品情報の編集フォーム表示フラグ
        model.addAttribute("productFormVisible", isProductFormVisible());

        //ユーザー情報
//        //全ユーザー取得
//         model.addAttribute("users",userService.getAllUser());
        //authorityKindが1のユーザーのみを取得
        model.addAttribute("users",userService. getPeopleWithAuthorityKindOne());

        //予約情報
        model.addAttribute("reservations",reservationService.getReservationProductDtoAll());

        return "/manage";

    }

        /** 商品情報の編集処理 */
        @RequestMapping("/edit-product/{productId}")
        public String editProduct(@PathVariable("productId") Long productId,@ModelAttribute ProductForm editedProductForm,Model model) {
            // 編集フォームを表示するためのフラグを設定
            productFormVisible = true;

            // 編集処理を実装
            Product editedProduct = converter.convertToEntity(editedProductForm);
            productService.updateProduct(productId, editedProduct);

            // 商品情報の編集が完了したのでフラグをリセット
            resetProductFormVisible();

            // 編集された商品情報をリクエスト属性に追加
            model.addAttribute("editedProduct", editedProductForm);

            return "redirect:/takeout/manage"; // 商品情報の一覧ページにリダイレクト

        }
        /** 商品情報の追加処理 */
        @PostMapping("/add-product")
        public String addProduct(@ModelAttribute ProductForm productForm) {
            Product newProduct = converter.convertToEntity(productForm);
            productService.addNewProduct(newProduct);

            return "redirect:/takeout/manage";
        }

//    /** 新規ユーザーの追加処理 */
//    @PostMapping("/add-user")
//    public String addUser(@ModelAttribute NewUserForm newUserForm Model model) {
          //ユーザーの追加処理を実装
          //userService.addUser(newUserForm);
//        // 新しいユーザーの情報を保存する処理を実装する

//        return "redirect:/takeout/manage"; // ユーザー情報の一覧ページにリダイレクト
//    }
//
//    /** ユーザー情報の編集処理 */
//    @PostMapping("/edit-user/{userId}")
//    public String editUser(@PathVariable Long userId,@ModelAttribute EditUserForm editUserForm) {
//        // ユーザーの編集処理を実装
//        userService.editUser(userId, editUserForm);
//        // ユーザーの情報を編集する処理を実装する

//        return "redirect:/takeout/manage"; // ユーザー情報の一覧ページにリダイレクト
//    }

}