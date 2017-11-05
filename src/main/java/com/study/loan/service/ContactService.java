package com.study.loan.service;

import com.study.loan.pojo.Contact;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
public interface ContactService {
    /**
     * 添加紧急联系人
     * @param contact
     * @return
     */
    int insert(Contact contact);
}
