package com.dujiaqi0725.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.dujiaqi0725.seckill.dao.ProductDAO;
import com.dujiaqi0725.seckill.dao.UserDAO;
import com.dujiaqi0725.seckill.dataobject.ProductDO;
import com.dujiaqi0725.seckill.dataobject.UserDO;
import com.dujiaqi0725.seckill.model.Paging;
import com.dujiaqi0725.seckill.model.Product;
import com.dujiaqi0725.seckill.model.User;
import com.dujiaqi0725.seckill.param.BasePageParam;
import com.dujiaqi0725.seckill.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public Product add(Product product) {
        if (product == null){
            return new Product();
        }
        ProductDO productDO = new ProductDO(product);
        productDAO.add(productDO);
        //增加商品的同时修改商品发布者的商品名称
        UserDO userDO = userDAO.selectById(product.getUser().getId());
        User user = userDO.toModel();
        List<Product> products = new ArrayList<>();
        String productNames = userDO.getProductNames();
        if (productNames != null && !productNames.isEmpty()) {
            List<String> productNamesList = JSON.parseArray(productNames, String.class);
            for (String productName : productNamesList) {
                ProductDO productDOByName = productDAO.selectByName(productName);
                products.add(productDOByName.toModel());
            }
            user.setProducts(products);
        }
        products.add(product);
        user.setProducts(products);
        userDAO.update(new UserDO(user));
        return productDO.toModel();
    }

    @Override
    public Product delete(Long productId) {
        if (productId == null) {
            return new Product();
        }
        //删除商品的同时修改商品发者的商品
        ProductDO productDO = productDAO.selectById(productId);
        UserDO userDO = userDAO.selectById(productDO.getUserId());
        User user = userDO.toModel();
        List<Product> products = new ArrayList<>();
        String productNames = userDO.getProductNames();
        if (productNames != null && !productNames.isEmpty()) {
            List<String> productNamesList = JSON.parseArray(productNames, String.class);
            for (String productName : productNamesList) {
                if (!Objects.equals(productName, productDO.getName())){
                    ProductDO productDOByName = productDAO.selectByName(productName);
                    products.add(productDOByName.toModel());
                }
            }
            user.setProducts(products);
            userDAO.update(new UserDO(user));
        }
        productDAO.delete(productId);
        return new Product();
    }

    @Override
    public Product update(Product product) {
        if (product == null) {
            return new Product();
        }
        //修改商品的同时修改发布者的商品
        ProductDO productDO = productDAO.selectById(product.getId());
        UserDO userDO = userDAO.selectById(productDO.getUserId());
        User user = userDO.toModel();
        List<Product> products = new ArrayList<>();
        String productNames = userDO.getProductNames();
        if (productNames != null && !productNames.isEmpty()) {
            List<String> productNamesList = JSON.parseArray(productNames, String.class);
            for (String productName : productNamesList) {
                if (!Objects.equals(productName, productDO.getName())){
                    ProductDO productDOByName = productDAO.selectByName(productName);
                    products.add(productDOByName.toModel());
                }
            }
            products.add(product);
            user.setProducts(products);
            userDAO.update(new UserDO(user));
        }
        productDAO.update(new ProductDO(product));
        return product;
    }

    @Override
    public Product findById(Long productId) {
        if (productId == null) {
            return new Product();
        }
        ProductDO productDO = productDAO.selectById(productId);
        Product product = productDO.toModel();
        product.setUser(userDAO.selectById(productDO.getUserId()).toModel());
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<ProductDO> productDOS = productDAO.selectAll();
        List<Product> products = new ArrayList<>();
        for (ProductDO productDO : productDOS) {
            products.add(productDO.toModel());
        }
        return products;
    }

    @Override
    public Product findByName(String name) {
        if (name == null || name.isEmpty()) {
            return new Product();
        }
        ProductDO productDO = productDAO.selectByName(name);
        Product product = productDO.toModel();
        product.setUser(userDAO.selectById(productDO.getUserId()).toModel());
        return product;
    }

    @Override
    public List<Product> findAllByName(String name) {
        List<Product> products = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            return products;
        }
        List<ProductDO> productDOS = productDAO.selectAllByName(name);
        productDOS.forEach(productDO -> {
            Product product = productDO.toModel();
            product.setUser(userDAO.selectById(productDO.getUserId()).toModel());
            products.add(product);
        });
        return products;
    }

    @Override
    public Paging<Product> pageQueryProduct(BasePageParam param) {
        Paging<Product> paging = new Paging<>();
        if (param == null) {
            param = new BasePageParam();
        }

        int count = productDAO.selectCount();

        if (count<1){
            paging.setTotalCount(0L);
            return paging;
        }

        List<ProductDO> productDOS = productDAO.pageQuery(param);
        List<Product> products = new ArrayList<>();

        for (ProductDO productDO : productDOS) {
            Product product = productDO.toModel();
            Long userId = productDO.getUserId();
            UserDO userDO = userDAO.selectById(userId);
            User user = userDO.toModel();
            product.setUser(user);
            products.add(product);
        }
        paging = new Paging<>(param.getPagination(),param.getPageSize(),(long) count,products);
        return paging;
    }
}
