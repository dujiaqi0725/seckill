package com.dujiaqi0725.seckill.model;

import java.io.Serializable;
import java.util.List;

public class Paging<R> implements Serializable {

    //页数
    private int pageNum = 1;

    //每页数量
    private int pageSize = 15;

    //总页数
    private int totalPage;

    //总记录数
    private Long totalCount;

    //集合数据
    private List<R> data;

    public Paging(){

    }

    public Paging(int pageNum, int pageSize, Long totalCount, List<R> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<R> getData() {
        return data;
    }

    public void setData(List<R> data) {
        this.data = data;
    }
}
