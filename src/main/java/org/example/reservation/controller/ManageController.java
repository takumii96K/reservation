package org.example.reservation.controller;

import java.util.List;

import org.example.reservation.entity.User;
import org.example.reservation.entity.converter.ProductFormConverter;
import org.example.reservation.entity.projection.ReservationProductDto;
import org.example.reservation.form.ProductForm;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.example.reservation.service.spec.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/takeout")
public class ManageController {
    /** DI対象 */
    private final ProductService productService;
    private final UserService userService;
    private final ReservationService reservationService;
    private final ProductFormConverter converter;



    /** 商品情報,ユーザー情報,予約情報の一覧を表示 */
    @GetMapping("/manage")
    public String showList(Model model) {
        //商品情報
        List<ProductForm> list = converter.convertToForm(productService.getAllProducts());
        //表示用「Model」への格納
        model.addAttribute("products" , list);


        /** ユーザー情報 */
//        //全ユーザー取得
//        List<User> ulist2=userService.getAllUser();
        //authorityKindが1のユーザーのみを取得
        List<User> ulist=userService. getPeopleWithAuthorityKindOne();
        //表示用「Model」への格納
        model.addAttribute("users", ulist);




        //予約情報
//		List<Reservation> rlist=reservationService.getReservationAll();
        List<ReservationProductDto> rlist2 = reservationService.getReservationProductDtoAll();
        //表示用「Model」への格納
//		model.addAttribute("reservations", rlist);
        model.addAttribute("reservations2", rlist2);

        return "/manage";

    }

}