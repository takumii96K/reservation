package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.service.spec.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("total", service.getSession().getCart().calculateTotalAmount());
        return "inCartList";
    }

}
