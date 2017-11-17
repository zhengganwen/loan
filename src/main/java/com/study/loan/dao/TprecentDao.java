package com.study.loan.dao;

import com.study.loan.core.PageBean;
import com.study.loan.pojo.Tapplication;
import com.study.loan.pojo.Tprecent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.10.30
 */
@Repository
public interface TprecentDao {
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
     * 查询利率信息
     * @param pageBean
     * @return
     */
    List<Tprecent> findrTprecentByPage(PageBean pageBean);

    /**
     * 查询总记录数
     */
    int countByPage();

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
