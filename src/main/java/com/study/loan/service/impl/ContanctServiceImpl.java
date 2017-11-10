package com.study.loan.service.impl;

import com.study.loan.dao.ContactDao;
import com.study.loan.pojo.Contact;
import com.study.loan.service.ContactService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
@Service
public class ContanctServiceImpl implements ContactService {
    @Resource
   private ContactDao contactDao;
    @Override
    public int insert(Contact contact) {
        return contactDao.insert(contact);
    }
}
