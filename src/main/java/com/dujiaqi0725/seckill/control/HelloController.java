package com.dujiaqi0725.seckill.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/index")
    public String hello(Model model){
        model.addAttribute("hello","welcome to seckill system");
        return "index.html";
    }

}
