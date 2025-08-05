package com.dujiaqi0725.seckill.param;

import java.io.Serializable;

public class BasePageParam implements Serializable {

    //页数
    private int pagination = 0;

    //一页的商品数
    private int pageSize = 10;

    public BasePageParam() {
    }

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
