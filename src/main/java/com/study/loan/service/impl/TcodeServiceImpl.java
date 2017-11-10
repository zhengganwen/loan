package com.study.loan.service.impl;

import com.study.loan.dao.TcodeDao;
import com.study.loan.pojo.Tcode;
import com.study.loan.service.TcodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TcodeServiceImpl implements TcodeService {
    @Resource
    private TcodeDao  tcodeDao;

    @Override
    public Tcode findTcode(Tcode tcode) {
        return tcodeDao.findTcode(tcode);
    }

    @Override
    public int insert(Tcode tcode) {
        return tcodeDao.insert(tcode);
    }
}
