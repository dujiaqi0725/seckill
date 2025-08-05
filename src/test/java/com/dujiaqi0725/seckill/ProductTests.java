package com.dujiaqi0725.seckill;

import com.dujiaqi0725.seckill.model.*;
import com.dujiaqi0725.seckill.service.ProductService;
import com.dujiaqi0725.seckill.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
//        for (int i = 1; i <= 5; i++) {
//            Product product = new Product();
//            String name = "洗衣液" + i;
//            product.setName(name);
//            product.setStatus(ProductStatus.ON);
//            product.setDescription("清洁衣物，不含有害添加剂");
//            product.setPrice( (double) (10+i));
//            product.setStock(100);
//            product.setUser(userService.findByUsername("洗衣液商贩"));
//            List<ImageResource> imageResources = new ArrayList<>();
//            ImageResource imageResource = new ImageResource();
//            imageResource.setName("牙膏"+ i + "样品图片");
//            imageResource.setDesc("超强清洁能力，不含有害身体物质");
//            imageResource.setUrl("www.yagao.com");
//            imageResources.add(imageResource);

//            product.setImages(imageResources);
//            //检验新增商品功能
//            Product add = productService.add(product);
//            Assert.assertNotNull("增加商品后的返回的商品对象不能为空",add);
//        }

//        //检验根据id查询商品功能
//        Product byId = productService.findById(10L);
//        System.out.println(byId.getUser());
//
//        //检验根据名称查询商品功能
//        Product productByName = productService.findByName("高档洗衣液");
//        System.out.println(productByName.toString());
//
//        //检验根据名称模糊查询商品功能
//        List<Product> products = productService.findAllByName("牙膏");
//        for (Product product : products) {
//            System.out.println(product.getName());
//        }

//        //检验修改商品功能
//        byId.setStock(90);
//        byId.setPrice(30.0);
//        byId.setStatus(ProductStatus.OFF);
//        byId.setName("高档洗衣液");
//        productService.update(byId);


//        //检验分页查询
//        BasePageParam param = new BasePageParam();
//        param.setPageSize(3);
//        param.setPagination(2);
//        Paging<Product> paging = productService.pageQueryProduct(param);
//        List<Product> data = paging.getData();
//        for (Product product : data) {
//            System.out.println(product.getName());
//        }
//
//
//
        //检验删除商品功能
        Product productByDelete = productService.findByName("高档洗衣液");
        productService.delete(productByDelete.getId());

    }


}
