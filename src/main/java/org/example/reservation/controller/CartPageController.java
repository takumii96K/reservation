package org.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.reservation.service.spec.ShoppingCartService;
import org.example.reservation.session.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inCart")
public class CartPageController {

    private final ShoppingCartService service;

    //カートの中身をみる(合計金額と
    @GetMapping("/list")
    public String InCartPage(Model model){
        model.addAttribute("cartItem", service.lookInCart());
        model.addAttribute("total", service.calculateTotalPrice());
        return "/inCartList";
    }
}
