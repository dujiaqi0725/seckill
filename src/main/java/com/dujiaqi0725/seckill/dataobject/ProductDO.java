package com.dujiaqi0725.seckill.dataobject;

import com.alibaba.fastjson.JSON;
import com.dujiaqi0725.seckill.model.ImageResource;
import com.dujiaqi0725.seckill.model.Product;
import com.dujiaqi0725.seckill.model.ProductStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;

public class ProductDO {

    private Long id;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    private Long userId;

    private String name;

    private String description;

    private String images;


    private String status;

    private Double price;

    private Integer stock;

    public ProductDO() {
    }

    public ProductDO(Product product){
        BeanUtils.copyProperties(product,this);
        if (product.getStatus().equals(ProductStatus.ON)){
            this.setStatus("ON");
        } else if (product.getStatus().equals(ProductStatus.OFF)) {
            this.setStatus("OFF");
        }
        this.setUserId(product.getUser().getId());
        if (!CollectionUtils.isEmpty(product.getImages())){
            this.setImages(JSON.toJSONString(product.getImages()));
        }
    }

    public Product toModel(){
        Product product = new Product();
        BeanUtils.copyProperties(this,product);
        if (this.getStatus().equals("ON")){
            product.setStatus(ProductStatus.ON);
        } else if (this.getStatus().equals("OFF")) {
            product.setStatus(ProductStatus.OFF);
        }
        product.setImages(JSON.parseArray(this.getImages(), ImageResource.class));
        return product;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
