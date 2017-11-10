package com.study.loan.controller;

import com.study.loan.pojo.Tadmin;
import com.study.loan.service.TadminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @Description
 * @author:Mr.Zheng
 * @Date:2017/10/28
 */
@Controller
@RequestMapping("/admin")
public class TadminController {
    @Resource
    private TadminService tadminService;
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     * 后台管理员登录
     * @param  tadmin
     * @return
     */
    @RequestMapping(value = "/dologin",method= RequestMethod.POST)
    public String dologin(Tadmin tadmin ){

        Tadmin admin =  tadminService.finAdmin(tadmin);
        if(admin != null){
            return "index";
        }else{
            return "login";
        }


    }



}
