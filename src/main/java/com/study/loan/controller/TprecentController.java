package com.study.loan.controller;

import com.study.loan.core.PageBean;
import com.study.loan.core.ResultMessage;
import com.study.loan.core.uuid;
import com.study.loan.pojo.Tprecent;
import com.study.loan.service.TprecentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:
 */
@Controller
@RequestMapping("/precent")
public class TprecentController {
    @Resource
    private TprecentService tprecentService;

    /**
     * 利率表
     * @return
     */
    @RequestMapping("/precentList")
    public String precentList(){
        return "precentList";
    }

    /**
     * 新增利率
     * @param tprecent
     * @return
     */
    @RequestMapping(value="/insert",method= RequestMethod.POST)
    @ResponseBody
    public ResultMessage insert(Tprecent tprecent){
        tprecent.setId(uuid.getInstance());
        int i = tprecentService.insert(tprecent);
        if(i==1){
            return new ResultMessage(true,"添加成功");
        }else{

            return new ResultMessage(false,"添加失败");
        }
    }

    /**
     * 更新利率
     * @return
     */
    @RequestMapping(value="/update",method= RequestMethod.POST)
    @ResponseBody
    public ResultMessage  update(Tprecent tprecent){
        int i =tprecentService.update(tprecent);
        if(i==1){
            return new ResultMessage(true,"更新成功");
        }else{
            return new ResultMessage(false,"更新失败");
        }
    }
    /**
     * web端用户分页查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value="/findPreByPage",method = RequestMethod.POST)
    @ResponseBody
    public Map findPreByPage( int page , int rows){
        PageBean<Tprecent> pageBean = tprecentService.findrTprecentByPage(page,rows);
        Map map =new HashMap();
        map.put("total",pageBean.getTotalSize());
        map.put("rows",pageBean.getDataList());
        return  map;
    }

    /**
     * 查询借款时间，利息
     * @return
     */
    @RequestMapping(value = "/findList")
    @ResponseBody
    public  ResultMessage findList(){
        List<Tprecent> list =tprecentService.findList();
       return new ResultMessage(true,"查询成功",list) ;
    }

    /**
     * 删除利率
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultMessage delete(String id){
        int i = tprecentService.delete(id);
        return  new ResultMessage(true,"删除成功");
    }



}
