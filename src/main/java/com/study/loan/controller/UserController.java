package com.study.loan.controller;

import com.study.loan.core.*;
import com.study.loan.dto.UserDto;
import com.study.loan.pojo.Tcode;
import com.study.loan.pojo.User;
import com.study.loan.service.TcodeService;
import com.study.loan.service.UserService;
import com.study.loan.util.MD5Util;
import com.study.loan.util.ranDom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description UserController
 * @author:Mr.Zheng
 * @Date:2017.10.28
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TcodeService tcodeService;

    /**
     * 用户页面
     * @return
     */
    @RequestMapping("/userList")
    public String userList(){
        return "userList";
    }


    /**
     *
     * @param user
     * @param session
     * @return ResultMessage
     */
    @RequestMapping(value="/dologin",method=RequestMethod.POST)
    @ResponseBody
    public ResultMessage  dalogin(@RequestBody User user, HttpSession session){
        //密码Md5校验
        user.setUserpwd(MD5Util.pwdDigest(user.getUserpwd()));
        User loginUser = userService.findUser(user,session);
        if(loginUser!=null){
            return new ResultMessage(true,"登录成功",loginUser);
        }else{
            return new ResultMessage(false,"登录失败");
        }
    }

    /**
     * 注册生成手机验证码
     * @param  userphone
     * @return
     */
    @RequestMapping(value="/getCard",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage getCard(@RequestParam String userphone ){
        //判断手机号是否注册过
        User  user =User.builder().userphone(userphone).build();
        List<User> list = userService.find(user);
        if(list.size() >0 ){
            return new  ResultMessage(false,"您的手机号码已注册");
        }else{
            //生成手机验证码
            String random = ranDom.getRandom();

            //调用短信接口



            //手机验证码插入数据库
            Tcode tcode  = Tcode.builder().id(uuid.getInstance()).userphone(userphone).vercode(random).build();
            //插入数据库操作
            int i =tcodeService.insert(tcode);
            return new  ResultMessage(true,"手机验证码已发送");
        }

    }

    /**
     * 手机号注册
     * @param userDto(userphone,userpwd,vercode)
     * @return
     */
    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage   sign(@RequestBody UserDto userDto){


            //进行手机验证码验证
            Tcode tcode  =Tcode.builder().userphone(userDto.getUserphone()).vercode(userDto.getVercode()).build();
            Tcode tTcode = tcodeService.findTcode(tcode);
            if(tTcode==null){
                return new ResultMessage(false,"手机验证码错误");
            }else{
                //比较的时间
                String time = Config.CompareDate();
                //数据库插入验证码的时间
                Date intime = tTcode.getIntime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //获取String类型的时间
                String createdate = sdf.format(intime);
                if(createdate.compareTo(time) >0){
                    User user =User.builder().id(uuid.getInstance()).userpwd(MD5Util.pwdDigest(userDto.getUserpwd())).userphone(userDto.getUserphone()).build();
                    int i = userService.insert(user);
                    if(i==1){
                        return  new ResultMessage(false,"注册成功");
                    }else{
                        return new ResultMessage(false,"注册失败");
                    }
                }else{
                    return new ResultMessage(false,"手机验证码过期");
                }
            }
    }

    /**
     * 重置密码生成手机验证码
     * @param userphone
     * @return
     */
    @RequestMapping(value="/resCard",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage resCard(@RequestParam String userphone){

        //生成手机验证码
        String random = ranDom.getRandom();
        //调用短信接口

        Tcode tcode  =Tcode.builder().id(uuid.getInstance()).userphone(userphone).vercode(random).build();
        //插入数据库操作
        int i =tcodeService.insert(tcode);
        return new  ResultMessage(true,"手机验证码已发送");
    }

    /**
     * 重置密码
     * @param userDto(userphone,userpwd,vercode)
     * @return
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public ResultMessage changePwd(@RequestBody UserDto userDto){

        //接受前台手机验证码
        Tcode tcode  = Tcode.builder().userphone(userDto.getUserphone()).vercode(userDto.getVercode()).build();
        Tcode tTcode = tcodeService.findTcode(tcode);
        if(tTcode==null){
            return new ResultMessage(false,"手机验证码错误");
        }else{
            //比较的时间
            String time = Config.CompareDate();
            //数据库插入验证码的时间
            Date intime = tTcode.getIntime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String createdate = sdf.format(intime);
            if(createdate.compareTo(time) >0){
                //查找用户
                User  user =User.builder().userphone(userDto.getUserphone()).build();
                List<User> list = userService.find(user);
                User  userPwd = list.get(0);
                userPwd.setUserpwd(MD5Util.pwdDigest(userDto.getUserpwd()));
                int i = userService.update(userPwd);
                if(i==1){
                    return new ResultMessage(true,"密码修改成功");
                }else{
                    return new ResultMessage(false,"密码修改失败");
                }

            }else{
                return new ResultMessage(false,"手机验证码过期");
            }

        }
    }

    /**
     * 实名认证
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value="/trueName",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage trueName(HttpServletRequest request, @RequestBody User user){
        //调用实名认证接口

        //从session获取到用户id
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        user.setId(loginUser.getId());
        user.setState(Config.user_state_Y);

        int  i = userService.update(user);
        if(i==0){
            return new  ResultMessage(false,"请重新提交实名认证");
        }else{
            return new  ResultMessage(false,"实名认证已提交，等待审批");
        }
    }


    /**
     * 更换手机号
     * @param userDto
     * @return
     */
    @RequestMapping(value="/changePhone",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage changPhone(@RequestBody UserDto userDto ){
        //接受前台手机验证码
        Tcode tcode  = Tcode.builder().userphone(userDto.getUserphone()).vercode(userDto.getVercode()).build();
        Tcode tTcode = tcodeService.findTcode(tcode);
        if(tTcode==null){
            return new ResultMessage(false,"手机验证码错误");
        }else{
            //比较的时间
            String time = Config.CompareDate();
            //数据库插入验证码的时间
            Date intime = tTcode.getIntime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            String createdate = sdf.format(intime);
            if(createdate.compareTo(time) >0){
                User user = User.builder().id(userDto.getId()).userphone(userDto.getUserphone()).userpwd(userDto.getUserpwd()).build();
                int i = userService.update(user);
                if(i==1){
                    return new ResultMessage(true,"请重新登录");
                }else{
                    return new ResultMessage(false,"绑定手机号失败");
                }
            }else{
                return new ResultMessage(false,"手机验证码过期");

            }

        }
    }

    /**
     * web端分页查询
     * @param user
     * @param page
     * @param rows
     * @return map
     */
    @RequestMapping(value="/findUserByPage",method = RequestMethod.POST)
    @ResponseBody
    public Map findUserByPage(User user , int page , int rows){
        PageBean<User>  pageBean = userService.findUserByPage(user ,page , rows);
        Map dataMap = new HashMap();
        List<User> list =pageBean.getDataList();
        for (User u:list) {
            if(u.getState().equals("1")){
                u.setState("用户已注册");
            }else if(u.getState().equals("2")){
                u.setState("用户已实名认证");
            }else if(u.getState().equals("3")){
                u.setState("用户已实名认证通过");
            }else if(u.getState().equals("4")){
                u.setState("用户已实名认证失败");
            }
        }
        dataMap.put("rows",list);
        dataMap.put("total",pageBean.getTotalSize());

       return dataMap;
    }

    /**
     * web对用户进行审批
     * @param user
     * @return
     */
    @RequestMapping("/updateState")
    public ResultMessage updateState(User user){
        int  i = userService.update(user);
        return new ResultMessage(true,"更新成功");
    }





}
