package com.study.loan.service;

import com.study.loan.pojo.Picture;

import java.util.List;

/**
 * @author Mr.Zheng
 * @Description 照片服务层
 * @Date 2017/11/14
 */
public interface PictureService {
    /**
     * 新增
     * @param picture
     * @return
     */
    int insert(Picture picture);

    /**
     * 更新照片
     * @param picture
     * @return int
     */
    int update(Picture picture);

    /**
     * 删除照片
     * @param id
     * @return int
     */
    int delete(String id);

    /**
     * 查询所有的集合
     * @return List
     */
    List<Picture> findPictureList();
}
