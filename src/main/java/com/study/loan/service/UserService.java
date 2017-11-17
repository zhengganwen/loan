package com.study.loan.service;

import com.study.loan.core.PageBean;
import com.study.loan.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
     /**
      * 登录验证
      * @param user
      * @return
      */
     public User findUser(User user, HttpSession session);

     /**
      * 添加用户
      * @param user
      * @return
      */
     public  int insert(User user);

     /**
      * 用户查询
      * @param user
      * @return
      */
     public List<User> find(User user);

     /**
      * 更新用户
      * @param user
      * @return
      */
     public  int  update(User user);

     /**
      * 用户分页查询
      * @param user
      * @param pageSize
      * @param pageNumber
      * @return
      */
     PageBean<User>  findUserByPage(User user, @RequestParam("page") int pageSize, @RequestParam("rows")int pageNumber);
}
