package com.study.loan.controller;

import com.study.loan.core.PageBean;
import com.study.loan.core.ResultMessage;
import com.study.loan.core.uuid;
import com.study.loan.dto.ApplicationDto;
import com.study.loan.pojo.Tprecent;
import com.study.loan.service.TapplicationService;
import com.study.loan.service.TprecentService;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.study.loan.pojo.Tapplication;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @author:Mr.Zheng
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
     * 用户申请
     * @return
     */
    @RequestMapping("/applicationList")
    public String userList(){
        return "applicationList";
    }

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
    public ResultMessage  update(Tapplication application){
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
    public Map findApplicationByPage(ApplicationDto application , int page , int rows){
        PageBean<Tapplication> pageBean = tapplicationService.findApplicationByPage(application ,page , rows);
        Map dataMap = new HashMap();
        List<Tapplication> list = pageBean.getDataList();
        for (Tapplication app : list) {
            if(app.getState().equals("1")){
                app.setState("已申请");
            }else if(app.getState().equals("2")){
                app.setState("已借款");
            }else if(app.getState().equals("3")){
                app.setState("已还款");
            }

        }
        dataMap.put("rows",list);
        dataMap.put("total",pageBean.getTotalSize());
        return dataMap;
    }


}
