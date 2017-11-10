package com.study.loan.core;

import java.util.List;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.10.28
 */
public class PageBean<T> {
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 页面记录数
     */
    private Integer pageSize;
    /**
     * 偏移量
     */
    private Integer offSet;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总记录数
     */
    private Integer totalSize;
    /**
     * 存放数据集合
     */
    private  List<T>  dataList;
    /**
     * 对象参数
     */
    private T  param;

    public PageBean() {}

    public PageBean(Integer pageNum,Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize=pageSize;
        //偏移量
        this.offSet=(pageNum-1)*pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        if(pageSize !=null) {
            //偏移量
            this.offSet = (pageNum - 1) * pageSize;
        }
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        if(pageNum!=null){
            //偏移量
            this.offSet = (pageNum - 1) * pageSize;
        }
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
