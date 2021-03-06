package com.study.loan.service.impl;

import com.study.loan.core.PageBean;
import com.study.loan.dao.TapplicationDao;
import com.study.loan.dto.ApplicationDto;
import com.study.loan.pojo.Tapplication;
import com.study.loan.service.TapplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
@Service
public class TapplicationServiceImpl  implements TapplicationService {


    @Resource
    private TapplicationDao tapplicationDao;
    @Override
    public int insert(ApplicationDto tapplication) {
        return tapplicationDao.insert(tapplication);
    }
    @Override
    public int update(Tapplication tapplication) {
        return tapplicationDao.update(tapplication);
    }
    @Override
    public PageBean<Tapplication> findApplicationByPage(ApplicationDto tapplication, int pageSize, int pageNumber) {
        ApplicationDto tapplicationParam = tapplication ==null ?new ApplicationDto():tapplication;
        int countAplication = tapplicationDao.countByPage(tapplicationParam);
        PageBean pageBean = new PageBean(pageSize,pageNumber);
        pageBean.setParam(tapplicationParam);
        List<Tapplication> tapplicationList = tapplicationDao.findApplicationByPage(pageBean);
        pageBean.setTotalSize(countAplication);
        pageBean.setDataList(tapplicationList);

        return pageBean;
    }

}
