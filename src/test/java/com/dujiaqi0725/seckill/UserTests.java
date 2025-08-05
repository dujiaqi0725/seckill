package com.dujiaqi0725.seckill;

import com.dujiaqi0725.seckill.model.Result;
import com.dujiaqi0725.seckill.model.User;
import com.dujiaqi0725.seckill.service.ProductService;
import com.dujiaqi0725.seckill.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("xiaoming");
        user.setPassword("123456");
        user.setAvatar("www.baidu.com");
        Result<User> register = userService.register(user);
        User data = register.getData();

//        //检验注册功能
//        Assert.assertNotNull("注册后返回的User对象不能为空",data);
//        Assert.assertTrue("注册后返回 User 对象的 id 值必须大于 0 。",data.getId() > 0);
//
//        //修改用户功能
//        User updateUser = userService.findByUsername("卖牙膏的");
//        updateUser.setUsername("牙膏商贩");
//        userService.update(updateUser);
//        User afterUpdate = userService.findByUserId(updateUser.getId());
//        System.out.println(afterUpdate);
//
//        //检验根据用户名查询功能
//        User byUsername = userService.findByUsername(user.getUsername());
//        System.out.println(byUsername);
//        Assert.assertNotNull("根据用户名查询已注册的用户不能为空",byUsername);
//
//        //检验根据用户id查询功能
//        User byUserId = userService.findByUserId(data.getId());
//        Assert.assertNotNull("根据id查询已注册的用户不能为空",byUserId);
//
//        //检验根据用户id查询多个用户功能
//        List<Long> userIds = new ArrayList<>();
//        userIds.add(1L);
//        userIds.add(2L);
//        List<User> byUserIds = userService.findByUserIds(userIds);
//        Assert.assertNotNull("根据id查询多个用户不能为空",byUserIds);
//
//        //检验修改功能(增加商品)
//        User yagaoUser = userService.findByUsername("牙膏商贩");
//        User xiyiyeUser = userService.findByUsername("洗衣液商贩");
//        List<Product> yagaoProducts = productService.findAllByName("牙膏");
//        List<Product> xiyiyrProducts = productService.findAllByName("洗衣液");
//        yagaoUser.setProducts(yagaoProducts);
//        xiyiyeUser.setProducts(xiyiyrProducts);
//        userService.update(yagaoUser);
//        userService.update(xiyiyeUser);

    }

}
