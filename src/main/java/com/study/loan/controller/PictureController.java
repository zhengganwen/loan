package com.study.loan.controller;

import com.study.loan.core.ResultMessage;
import com.study.loan.core.uuid;
import com.study.loan.pojo.Picture;
import com.study.loan.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Mr.Zheng
 * @Description  PictureController
 * @Date 2017/11/14
 */
@Controller
@RequestMapping("/picture")
public class PictureController {
    @Resource
    private PictureService pictureService;


    /**
     * 照片表
     * @return
     */
    @RequestMapping("/pictureList")
    public String pictureList(){
        return "pictureList";
    }
    /**
     * 新增照片
     * @param picture
     * @return ResultMessage
     */
    @RequestMapping(value = "/insert" ,method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage insert( Picture picture){
        picture.setId(uuid.getInstance());
        int i = pictureService.insert(picture);
        return new ResultMessage(true,"新增成功") ;
    }

    /**
     * 查询照片集合
     * @return ResultMessage
     */
    @RequestMapping("/findPictureList")
    @ResponseBody
    public List findLlist(){
        List<Picture> list =pictureService.findPictureList();
        return list;
    }

    /**
     * 删除照片
     * @param id
     * @return ResultMessage
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage delete(String id){
        int i = pictureService.delete(id);
        return new ResultMessage(true,"删除成功");
    }

}
