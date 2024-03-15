package org.example.reservation.controller;

import org.example.reservation.entity.converter.ProductDtoConverter;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.example.reservation.service.spec.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ManageController {
    /** DI対象 */
    private final ProductService productService;
    private final UserService userService;
    private final ReservationService reservationService;
    private final ProductDtoConverter converter;

    /**
     * 商品情報,ユーザー情報,予約情報の一覧を表示
     * @param model products:ProductDto, users, reservation
     * @return view: manage.html
     */
    @GetMapping("/takeout/manage")
    public String showList(Model model) {
        //
        model.addAttribute("products" , converter.convertToDtoList(productService.getAllProducts()));

        model.addAttribute("users", userService.findUserWithAuthorityKindOne());

        model.addAttribute("reservations", reservationService.getReservationProductDtoAll());

        return "/manage";
    }
}