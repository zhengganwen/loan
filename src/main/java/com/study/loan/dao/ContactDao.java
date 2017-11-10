package com.study.loan.dao;

import com.study.loan.pojo.Contact;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @author:Mr.Zheng
 * @Date:
 */
@Repository
public interface ContactDao {
    /**
     * 添加晋级联系人
     * @param contact
     * @return
     */
    int insert(Contact contact);
}
