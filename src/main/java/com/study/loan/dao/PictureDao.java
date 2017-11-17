package com.study.loan.dao;

import com.study.loan.pojo.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.Zheng
 * @Description
 * @Date 2017/11/14
 */
@Repository
public interface PictureDao {
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
    List<Picture>  findPictureList();
}
