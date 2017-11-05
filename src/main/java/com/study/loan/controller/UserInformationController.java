package com.study.loan.controller;

import com.study.loan.core.ResultMessage;
import com.study.loan.core.uuid;
import com.study.loan.pojo.Tuserinformation;
import com.study.loan.service.TuserinformationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
@RestController
@RequestMapping("/information")
public class UserInformationController {
    @Resource
    private TuserinformationService tuserinformationService;

    @RequestMapping(value = "/insert")
    public ResultMessage insert(@RequestBody Tuserinformation tuserinformation){
        tuserinformation.setId(uuid.getInstance());
        int i = tuserinformationService.insert(tuserinformation);
        if(i==1){
           return new ResultMessage(true,"添加成功") ;
        }else{
            return new ResultMessage(false,"添加失败") ;
        }
    }




}
