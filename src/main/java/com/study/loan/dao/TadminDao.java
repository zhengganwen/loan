package com.study.loan.dao;

import com.study.loan.pojo.Tadmin;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.10.28
 */
@Repository
public interface TadminDao {
    /**
     * web端登录
     * @param tadmin
     * @return
     */
    Tadmin finAdmin(Tadmin tadmin);
}
