package com.study.loan.service;

import com.study.loan.pojo.Tuserinformation;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
public interface TuserinformationService {
    /**
     * 添加用户信息
     * @param tuserinformation
     * @return
     */
    int insert(Tuserinformation tuserinformation);
}
