package com.dujiaqi0725.seckill.service;

import com.dujiaqi0725.seckill.model.Paging;
import com.dujiaqi0725.seckill.model.Product;
import com.dujiaqi0725.seckill.param.BasePageParam;

import java.util.List;

public interface ProductService {

    //增加商品
    Product add(Product product);

    //删除商品
    Product delete(Long productId);

    //更新商品信息
    Product update(Product product);

    //根据id查询商品
    Product findById(Long productId);

    List<Product> findAll();

    //根据商品名称查询商品
    Product findByName(String name);

    List<Product> findAllByName(String name);

    //分页查询商品
    Paging<Product> pageQueryProduct(BasePageParam param);

}
