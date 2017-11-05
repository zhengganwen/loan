package com.study.loan.dao;

import com.study.loan.pojo.Tuserinformation;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.11.2
 */
@Repository
public interface TuserinformationDao {
    /**
     * 添加用户信息
     * @param tuserinformation
     * @return
     */
    int insert(Tuserinformation tuserinformation);
}
