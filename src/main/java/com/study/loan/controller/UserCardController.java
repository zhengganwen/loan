package com.study.loan.controller;

import com.study.loan.core.ResultMessage;
import com.study.loan.core.uuid;
import com.study.loan.pojo.Tusercard;
import com.study.loan.service.UserCardService;
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
@RequestMapping("/userCard")
public class UserCardController {
    @Resource
    private UserCardService userCardService;

    @RequestMapping(value = "insert")
    public ResultMessage insert(@RequestBody Tusercard tusercard){
        tusercard.setId(uuid.getInstance());
        int i = userCardService.insert(tusercard);
        if(i==1){
            return new ResultMessage(true,"绑定成功");
        }else{
            return new ResultMessage(false,"绑定失败");
        }
    }
}
