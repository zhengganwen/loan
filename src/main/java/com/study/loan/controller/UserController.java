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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.10.28
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TcodeService tcodeService;


    @RequestMapping("/userList")
    public  String userList(){
        return "userList";
    }
    /**
     *
     * @param user 用户登录,参数(userphone,userpwd)
     * @return
     */
    @RequestMapping(value="/dologin",method=RequestMethod.POST)
    @ResponseBody
    public ResultMessage  dalogin(@RequestBody User user){
        //密码Md5校验
        user.setUserpwd(MD5Util.pwdDigest(user.getUserpwd()));
        User loginUser = userService.findUser(user);
        if(loginUser!=null){
            return new ResultMessage(true,"登录成功",loginUser);
        }else{
            return new ResultMessage(false,"登录失败");
        }
    }

    /**
     * 手机号注册
     * @param userDto(userphone,userpwd,vercode)
     * @return
     */
    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage   sign(@RequestBody UserDto userDto) throws Exception {

            //进行手机验证码验证
            Tcode tcode  =Tcode.builder().userphone(userDto.getUserphone()).vercode(userDto.getVercode()).build();
            Tcode tTcode = tcodeService.findTcode(tcode);
            if(tTcode==null){
                return new ResultMessage(false,"手机验证码错误");
            }else{
                String time = Config.CompareDate(); //比较的时间
                Date intime = tTcode.getIntime();  //数据库插入验证码的时间
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
     * 重置密码
     * @param userDto(userphone,userpwd,vercode)
     * @return
     * @throws Exception
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public ResultMessage changePwd(@RequestBody UserDto userDto) throws Exception {

        //接受前台手机验证码
        Tcode tcode  = Tcode.builder().userphone(userDto.getUserphone()).vercode(userDto.getVercode()).build();
        Tcode tTcode = tcodeService.findTcode(tcode);
        if(tTcode==null){
            return new ResultMessage(false,"手机验证码错误");
        }else{
            String time = Config.CompareDate(); //比较的时间
            Date intime = tTcode.getIntime();  //数据库插入验证码的时间
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
     * 注册生成手机验证码
     * @param userphone
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/getCard",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage getCard(@RequestParam String userphone ) throws IOException {
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
            int i =tcodeService.insert(tcode);   //插入数据库操作
            return new  ResultMessage(true,"手机验证码已发送");
        }

    }

    /**
     * 重置密码生成手机验证码
     * @param userphone
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/resCard",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage resCard(@RequestParam String userphone){

        //生成手机验证码
        String random = ranDom.getRandom();
        //调用短信接口

        Tcode tcode  =Tcode.builder().id(uuid.getInstance()).userphone(userphone).vercode(random).build();
        int i =tcodeService.insert(tcode);   //插入数据库操作
        return new  ResultMessage(false,"手机验证码已发送");
    }

    /**
     * 实名认证
     * @param user(id,username,usercardnumber,frontpicture,backpicture,truepicture)
     * @return
     */
    @RequestMapping(value="/trueName",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage trueName(@RequestBody User user){
        //调用实名认证接口
        user.setState(Config.user_state_Y);
        int  i = userService.update(user);
        if(i==0){
            return new  ResultMessage(false,"请重新提交实名认证");
        }else{
            return new  ResultMessage(false,"实名认证已提交，等待审批");
        }

    }

    /**
     * web端用户分页查询
     * @param user
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/findUserByPage",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage findUserByPage(@RequestBody User user ,int page ,int rows){
        PageBean<User>  pageBean = userService.findUserByPage(user ,page , rows);

       return new ResultMessage(true,"查询成功",pageBean);
    }

    /**
     * 更换手机号
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
            String time = Config.CompareDate(); //比较的时间
            Date intime = tTcode.getIntime();  //数据库插入验证码的时间
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





}
