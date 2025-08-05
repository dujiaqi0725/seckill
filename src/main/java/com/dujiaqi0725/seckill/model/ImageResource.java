package com.dujiaqi0725.seckill.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "图片",description = "方便进行保存图片属性")
public class ImageResource implements Serializable {

    //图片名称
    @ApiModelProperty(value = "图片名称")
    private String name;


    //图片url
    @ApiModelProperty(value = "图片url")
    private String url;

    //图片描述
    @ApiModelProperty(value = "图片描述")
    private String desc;

    public ImageResource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
