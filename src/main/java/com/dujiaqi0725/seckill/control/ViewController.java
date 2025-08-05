package com.dujiaqi0725.seckill.control;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }


    @GetMapping("/addProduct")
    public String addProduct(){
        return "addProduct.html";
    }

    @GetMapping("/register")
    public String register(){
        return "register.html";
    }

}
