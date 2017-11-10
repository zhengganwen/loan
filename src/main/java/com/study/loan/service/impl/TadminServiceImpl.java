package com.study.loan.service.impl;


import com.study.loan.dao.TadminDao;
import com.study.loan.pojo.Tadmin;
import com.study.loan.service.TadminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:20171028
 */
@Service
public class TadminServiceImpl implements TadminService {
    @Resource
    private TadminDao tadminDao;

    @Override
    public Tadmin finAdmin(Tadmin tadmin) {
        return tadminDao.finAdmin(tadmin);
    }
}
