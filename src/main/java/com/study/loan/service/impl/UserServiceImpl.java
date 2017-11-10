package com.study.loan.service.impl;

import com.study.loan.core.AttachesConfig;
import com.study.loan.core.PageBean;
import com.study.loan.core.uuid;
import com.study.loan.dao.UserDao;
import com.study.loan.pojo.User;
import com.study.loan.service.UserService;
import com.study.loan.util.PictureUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public List<User> find(User user) {
        return userDao.find(user);
    }

    @Override
    public User findUser(User user, HttpSession session) {
        User loginUser = userDao.findUser(user);
        session.setAttribute("loginUser",loginUser);
        return loginUser;
    }

    @Override
    public int update(User user) {
        if(user.getFrontpicture()!=null){
            //身份证正面
            String  frongtPictureid = uuid.getInstance();
            // 本地存储照片
            PictureUtil.picture(user.getFrontpicture(), AttachesConfig.getValue("pics_local_path"), frongtPictureid+".png");
            // 照片访问路径
            String frontPicurl = AttachesConfig.getValue("pics_url_root")+frongtPictureid+".png";
            user.setFrontpicture(frontPicurl);
        }
       if(user.getBackpicture()!=null){
           //身份证反面
           String  backPictureid = uuid.getInstance();
           PictureUtil.picture(user.getBackpicture(), AttachesConfig.getValue("pics_local_path"), backPictureid+".png");
           String backPicurl = AttachesConfig.getValue("pics_url_root")+backPictureid+".png";
           user.setFrontpicture(backPicurl);

       }

     if(user.getTruepicture()!=null){
         //自拍证明
         String  truePictureid = uuid.getInstance();
         PictureUtil.picture(user.getTruepicture(), AttachesConfig.getValue("pics_local_path"), truePictureid+".png");
         String truePicurl = AttachesConfig.getValue("pics_url_root")+truePictureid+".png";
         user.setFrontpicture(truePicurl);
     }

        return userDao.update(user);
    }

    @Override
    public PageBean<User> findUserByPage(@RequestBody User user, @RequestParam("page") int pageSize, @RequestParam("rows")int pageNumber) {
        User userParam = user ==null ?new User():user;
        int countUser = userDao.countByPage(userParam);
        PageBean pageBean = new PageBean(pageSize,pageNumber);
        pageBean.setParam(userParam);
        List<User> userList = userDao.findUserByPage(pageBean);
        pageBean.setTotalSize(countUser);
        pageBean.setDataList(userList);

        return pageBean;
    }


}
