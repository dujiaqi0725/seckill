package com.dujiaqi0725.seckill.control;

import com.dujiaqi0725.seckill.model.*;
import com.dujiaqi0725.seckill.service.ProductService;
import com.dujiaqi0725.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    //在页面显示用户信息并显示用户商品
    @GetMapping("/user")
    public String getUser(Model model){
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<User> users = userService.findByUserIds(userIds);
        model.addAttribute("users",users);
        return "user";
    }


    //在页面显示商品信息
    @GetMapping("/product")
    public String getProduct(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "product";
    }


    @ResponseBody
    @PostMapping("/login")
    public Result<User> login(@RequestBody Map<String , String> user){
        String username = user.get("username");
        String password = user.get("password");
        return userService.login(username,password);
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String username , @RequestParam String name , @RequestParam String description ,
                             @RequestParam Double price , @RequestParam Integer stock, @RequestParam List<String> imageUrls){
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setStatus(ProductStatus.ON);

        User user = userService.findByUsername(username);
        product.setUser(user);

        List<ImageResource> image = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            ImageResource imageResource = new ImageResource();
            imageResource.setUrl(imageUrl);
            image.add(imageResource);
        }

        product.setImages(image);

        productService.add(product);
        return "/product";
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(@RequestBody Map<String,String> userInfo) {
        try {
            User user = new User();
            String username = userInfo.get("username");
            String password = userInfo.get("password");
            String avatar = userInfo.get("avatar");

            user.setUsername(username);
            user.setPassword(password);
            user.setAvatar(avatar);

            userService.register(user);

            // 返回成功响应
            return Map.of("success", true, "message", "注册成功");
        } catch (Exception e) {
            // 处理异常，返回错误信息
            return Map.of("success", false, "message", "注册失败: " + e.getMessage());
        }
    }

}
