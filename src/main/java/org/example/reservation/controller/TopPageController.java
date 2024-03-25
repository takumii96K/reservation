package org.example.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/takeout") //TopURL
public class TopPageController {


    @GetMapping("/top") //home
    public String topPage(Model model){
        model.addAttribute("title", "トップページ");
        return "/top";
    }

    @GetMapping("/member")
    public String memberPage(){
        return "member";
    }
}
