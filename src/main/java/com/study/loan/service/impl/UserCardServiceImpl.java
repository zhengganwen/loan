package com.study.loan.service.impl;

import com.study.loan.dao.UserCardDao;
import com.study.loan.pojo.Tusercard;
import com.study.loan.service.UserCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
@Service
public class UserCardServiceImpl implements UserCardService {
    @Resource
    private UserCardDao userCardDao;

    @Override
    public int insert(Tusercard tusercard) {
        return userCardDao.insert(tusercard);
    }
}
