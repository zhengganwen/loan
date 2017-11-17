package com.study.loan.service;

import com.study.loan.core.PageBean;
import com.study.loan.pojo.Tapplication;
import com.study.loan.pojo.Tprecent;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
public interface TprecentService {
    /**
     * 新增利率
     * @param tprecent
     * @return
     */
    int insert(Tprecent tprecent);

    /**
     * 更新利率
     * @param tprecent
     * @return
     */
    int update(Tprecent tprecent);

    /**
     * 利率分页查询
     * @param
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageBean<Tprecent> findrTprecentByPage( @RequestParam("page") int pageSize, @RequestParam("rows")int pageNumber);

    /**
     * 查询利率数据
     * @return
     */
    List<Tprecent> findList();

    /**
     * 删除利率
     * @param id
     * @return
     */
    int delete(String id);
}
