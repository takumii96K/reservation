package org.example.reservation.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.reservation.service.spec.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inCart")
public class CartController {

    private final ShoppingCartService service;

    /**
     * カートの中身を表示
     * @param model cartItem, total
     * @return view:"inCartList"
     */
    @GetMapping("/list")
    public String InCartPage(Model model){
        model.addAttribute("cartItems", service.lookInCart());
        return "/inCartList";
    }
//    @GetMapping("/checkout")
//    public String showCheckout(HttpSession session, RedirectAttributes redirectAttributes) {
//        Reservation reservation = (Reservation) session.getAttribute("reservation");
//        if (reservation == null) {
//            // 予約者情報がセッションに存在しない場合、入力画面にリダイレクト
//            return "redirect:/reservation/form";
//        } else {
//            // 予約者情報が存在する場合、決済画面に進む
//            return "redirect:/payment";
//        }
//    }


}
