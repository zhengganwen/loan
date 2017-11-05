package com.study.loan.dao;

import com.study.loan.pojo.Tusercard;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.11.2
 */
@Repository
public interface UserCardDao {
    /**
     * 关联银行卡
     * @param tusercard
     * @return
     */
    int insert(Tusercard tusercard);
}
