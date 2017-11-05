package com.study.loan.service.impl;

import com.study.loan.dao.TuserinformationDao;
import com.study.loan.pojo.Tuserinformation;
import com.study.loan.service.TuserinformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.11.2
 */
@Service
public class TuserinformationServiceImpl implements TuserinformationService {
    @Resource
    private TuserinformationDao tuserinformationDao;

    public int insert(Tuserinformation tuserinformation) {
        return tuserinformationDao.insert(tuserinformation);
    }
}
