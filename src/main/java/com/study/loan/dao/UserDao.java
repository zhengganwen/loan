package com.study.loan.dao;

import com.study.loan.core.PageBean;
import com.study.loan.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 登录验证
     * @param user
     * @return
     */
    public User  findUser(User user);
    /**
     * 添加用户
     * @param user
     * @return
     */
    public int insert(User user);
    /**
     * 用户查询
     * @param user
     * @return
     */
    public List<User>  find(User user);
    /**
     * 更新用户
     * @param user
     * @return
     */
    public  int  update(User user);

    /**
     * 用户分页查询
     * @param pageBean
     * @return
     */
    List<User>  findUserByPage(PageBean pageBean);
    /**
     * 查询总记录数
     */
    int countByPage(User user);



}
