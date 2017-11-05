package com.study.loan.service;


import com.study.loan.pojo.Tcode;

public interface TcodeService {
    /**
     * 手机验证码识别
     * @param tcode
     * @return
     */
    public Tcode findTcode(Tcode tcode);

    /**
     * 添加短信验证码
     * @param tcode
     * @return
     */

    public int insert(Tcode tcode);    //插入短信
}
