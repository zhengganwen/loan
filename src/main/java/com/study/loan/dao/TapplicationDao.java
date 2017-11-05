package com.study.loan.dao;

import com.study.loan.core.PageBean;
import com.study.loan.dto.ApplicationDto;
import com.study.loan.pojo.Tapplication;
import com.study.loan.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.10.30
 */
@Repository
public interface TapplicationDao {
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
     * 借款申请分页查询
     * @param pageBean
     * @return
     */
    List<Tapplication> findApplicationByPage(PageBean pageBean);
    /**
     * 查询总记录数
     */
    int countByPage(Tapplication tapplication);
}
