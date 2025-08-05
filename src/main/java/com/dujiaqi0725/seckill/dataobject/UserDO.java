package com.dujiaqi0725.seckill.dataobject;

import com.alibaba.fastjson.JSON;
import com.dujiaqi0725.seckill.model.Product;
import com.dujiaqi0725.seckill.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDO {

    private Long id;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    private String username;

    private String password;

    private String avatar;

    private String productNames;

    public UserDO() {
    }

    public UserDO(User user){
        BeanUtils.copyProperties(user,this);
        List<Product> products = user.getProducts();
        List<String> productNames = new ArrayList<>();
        if (!CollectionUtils.isEmpty(products)) {
            products.forEach(product -> {
                productNames.add(product.getName());
            });
        }
        this.setProductNames(JSON.toJSONString(productNames));
    }

    public User toModel(){
        User user = new User();
        BeanUtils.copyProperties(this,user);
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }
}
