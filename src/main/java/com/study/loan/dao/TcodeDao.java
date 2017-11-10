package com.study.loan.dao;

import com.study.loan.pojo.Tcode;
import org.springframework.stereotype.Repository;

@Repository
public interface TcodeDao {

    /**
     *  手机验证码识别
     * @param tcode
     * @return Tcode
     */
     Tcode findTcode(Tcode tcode);

    /**
     * 添加短信验证码
     * @param tcode
     * @return int
     */
     int insert(Tcode tcode);

}
