package com.dujiaqi0725.seckill.service;

import com.dujiaqi0725.seckill.model.Result;
import com.dujiaqi0725.seckill.model.User;

import java.util.List;

public interface UserService {

    /**
     * 注册用户
     * @param user 用户信息
     * @return 注册信息
     */
    Result<User> register(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录信息
     */
    Result<User> login(String username , String password);

    //根据用户名查询用户
    User findByUsername(String username);

    //根据用户id查询用户
    User findByUserId(Long userId);

    //根据用户id查询多个用户
    List<User> findByUserIds(List<Long> userIds);

    //修改用户商品
    User update(User user);

}
