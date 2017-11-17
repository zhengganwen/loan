package com.study.loan.service.impl;

import com.study.loan.core.PageBean;
import com.study.loan.dao.TprecentDao;
import com.study.loan.pojo.Tprecent;
import com.study.loan.pojo.User;
import com.study.loan.service.TprecentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
@Service
public class TprecentImpl implements TprecentService {


    @Resource
    private TprecentDao tprecentDao;

    @Override
    public int insert(Tprecent tprecent) {
        return tprecentDao.insert(tprecent);
    }

    @Override
    public int update(Tprecent tprecent) {
        return tprecentDao.update(tprecent);
    }

    @Override
    public PageBean<Tprecent> findrTprecentByPage(int pageSize, int pageNumber) {
        int countTpre = tprecentDao.countByPage();
        PageBean pageBean = new PageBean(pageSize,pageNumber);
        List<Tprecent> tprecentsList = tprecentDao.findrTprecentByPage(pageBean);
        pageBean.setTotalSize(countTpre);
        pageBean.setDataList(tprecentsList);

        return pageBean;
    }

    @Override
    public List<Tprecent> findList() {
        return tprecentDao.findList();
    }

    @Override
    public int delete(String id) {
        return tprecentDao.delete(id);
    }


}
