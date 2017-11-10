package com.study.loan.service;

import com.study.loan.core.PageBean;
import com.study.loan.dto.ApplicationDto;
import com.study.loan.pojo.Tapplication;
import com.study.loan.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
@Service
public interface TapplicationService {
    /**
     * 新增借款请求
     * @param tapplication
     * @return
     */
    int insert(ApplicationDto tapplication);

    /**
     * 更新借款申请
     * @param tapplication
     * @return
     */
    int update(Tapplication tapplication);

    /**
     * 借款分页查询
     * @param tapplication
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageBean<Tapplication> findApplicationByPage(ApplicationDto tapplication, @RequestParam("page") int pageSize, @RequestParam("rows")int pageNumber);

}
