
package org.example.reservation.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.example.reservation.entity.Product;
import org.example.reservation.entity.converter.ProductFormConverter;
import org.example.reservation.entity.projection.ReservationProductDto;
import org.example.reservation.form.ProductForm;
import org.example.reservation.service.spec.ProductService;
import org.example.reservation.service.spec.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/takeout")
@RequiredArgsConstructor
public class ManageController {
    /** DI対象 */
    private final ProductService productService;
    private final ReservationService reservationService;

    ProductFormConverter productFormConverter=new ProductFormConverter();
//	UserRegistrationFormConverter userRegistrationFormConverter=new UserRegistrationFormConverter();


    /** 商品情報,ユーザー情報,予約情報の一覧を表示 */
    @GetMapping("/manage")
    public String showList(Model model) {
        //商品情報
        List<Product> list=productService.getAllProducts();
        List<ProductForm> list2=productFormConverter.convertToForm(list);
        //表示用「Model」への格納
        model.addAttribute("products" ,list2);

        /** ユーザー情報 */


        /** 予約情報 */
//		List<Reservation> rlist=reservationService.getReservationAll();
        List<ReservationProductDto> rlist2=reservationService.getReservationProductDtoAll();
        //表示用「Model」への格納
//		model.addAttribute("reservations", rlist);
        model.addAttribute("reservations2", rlist2);


        return "/manage";

    }

}