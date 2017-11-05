package com.study.loan.controller;

import com.study.loan.core.PageBean;
import com.study.loan.core.ResultMessage;
import com.study.loan.core.uuid;
import com.study.loan.dto.ApplicationDto;
import com.study.loan.pojo.Tprecent;
import com.study.loan.service.TapplicationService;
import com.study.loan.service.TprecentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.study.loan.pojo.Tapplication;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
@Controller
@RequestMapping("/application")
public class TapplicationController {
    @Resource
    private TapplicationService tapplicationService;
    @Resource
    private TprecentService tprecentService;

    /**
     * 借款记录添加
     * @param application
     * @return
     */
    @RequestMapping(value="/insert",method= RequestMethod.POST)
    @ResponseBody
    public ResultMessage  insert(@RequestBody ApplicationDto application){
        application.setId(uuid.getInstance());
        Double money = application.getMoney();
        Double gerDate = Double.valueOf(application.getGetdate());
        Double returnMoney = money * application.getPercent()*gerDate;
       application.setReturnmoney(returnMoney);

        int i =tapplicationService.insert(application);
        if(i==1){
            return new ResultMessage(true,"申请成功",application) ;
        }else{
            return new ResultMessage(true,"申请失败") ;
        }
    }

    /**
     * 借款记录更新
     * @param application
     * @return
     */
    @RequestMapping(value="/update",method= RequestMethod.POST)
    @ResponseBody
    public ResultMessage  update(@RequestBody Tapplication application){
        int  i = tapplicationService.update(application);
        if(i==0){
            return new  ResultMessage(false,"更新失败");
        }else{
            return new  ResultMessage(false,"更新成功");
        }
    }

    /**
     * 借款记录查询
     * @param application
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/findApplicationByPage",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage findApplicationByPage(@RequestBody Tapplication application , int page , int rows){
        PageBean<Tapplication> pageBean = tapplicationService.findApplicationByPage(application ,page , rows);

        return new ResultMessage(true,"查询成功",pageBean);
    }


}
