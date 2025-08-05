package com.dujiaqi0725.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.dujiaqi0725.seckill.dao.ProductDAO;
import com.dujiaqi0725.seckill.dao.UserDAO;
import com.dujiaqi0725.seckill.dataobject.ProductDO;
import com.dujiaqi0725.seckill.dataobject.UserDO;
import com.dujiaqi0725.seckill.model.Product;
import com.dujiaqi0725.seckill.model.Result;
import com.dujiaqi0725.seckill.model.User;
import com.dujiaqi0725.seckill.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Result<User> register(User user) {
        Result<User> result = new Result<>();
        if (user == null){
            result.setSuccess(false);
            result.setCode("400");
            result.setMessage("用户为空");
            return result;
        }

        if (userDAO.selectByUsername(user.getUsername()) != null){
            result.setSuccess(false);
            result.setCode("401");
            result.setMessage("用户名已经存在");
            return result;
        }

        String saltPwd = user.getPassword() + "_ykd2025";
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();
        user.setPassword(md5Pwd);

        UserDO userDO = new UserDO(user);
        int add = userDAO.add(userDO);
        if (add <1){
            return result;
        }
        result.setData(userDO.toModel());
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<User> login(String username, String password) {
        Result<User> result = new Result<>();
        if (username == null || username.isEmpty()) {
            result.setCode("600");
            result.setMessage("用户名为空");
            result.setSuccess(false);
            return result;
        }

        if (password == null || password.isEmpty()) {
            result.setCode("601");
            result.setMessage("密码为空");
            result.setSuccess(false);
            return result;
        }

        if (userDAO.selectByUsername(username) == null){
            result.setCode("602");
            result.setMessage("用户名不存在");
            result.setSuccess(false);
            return result;
        }

        UserDO userDO = userDAO.selectByUsername(username);

        String saltPwd = password + "_ykd2025";
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();
        if (!StringUtils.equals(md5Pwd,userDO.getPassword())){
            result.setCode("603");
            result.setMessage("密码错误");
            result.setSuccess(false);
            return result;
        }
        User user = fillProduct(userDO.toModel(), userDO.getProductNames());
        user.setPassword(null);
        result.setSuccess(true);
        result.setData(user);
        return result;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return new User();
        }
        UserDO userDO = userDAO.selectByUsername(username);
        return fillProduct(userDO.toModel(),userDO.getProductNames());
    }

    @Override
    public User findByUserId(Long userId) {
        if (userId == null) {
            return new User();
        }
        UserDO userDO = userDAO.selectById(userId);
        return fillProduct(userDO.toModel(), userDO.getProductNames());
    }

    @Override
    public List<User> findByUserIds(List<Long> userIds) {
        List<User> users = new ArrayList<>();
        if (CollectionUtils.isEmpty(userIds)){
            return users;
        }
        List<UserDO> userDOS = userDAO.selectByIds(userIds);
        userDOS.forEach(userDO -> {
            User user = fillProduct(userDO.toModel(), userDO.getProductNames());
            users.add(user);
        });
        return users;
    }

    @Override
    public User update(User user) {
        if (user == null) {
            return new User();
        }
        userDAO.update(new UserDO(user));
        return user;
    }


    private User fillProduct(User user, String productNames){
        if (productNames != null && !productNames.isEmpty()) {
            List<String> productNamesList = JSON.parseArray(productNames, String.class);
            List<Product> products = new ArrayList<>();
            for (String productName : productNamesList) {
                ProductDO productDO = productDAO.selectByName(productName);
                products.add(productDO.toModel());
            }
            user.setProducts(products);
        }
        return user;
    }
}
