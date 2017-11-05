package com.study.loan.controller;

import com.study.loan.core.ResultMessage;
import com.study.loan.core.uuid;
import com.study.loan.pojo.Contact;
import com.study.loan.service.ContactService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.11.2
 */
@RestController
@RequestMapping("/contact")
public class ContactController {
    @Resource
    private ContactService contactService;

    /**
     * 添加紧急联系人
     * @param contact
     * @return
     */
    @RequestMapping(value = "/insert")
    public ResultMessage insert(@RequestBody Contact contact){
        contact.setId(uuid.getInstance());
        int i = contactService.insert(contact);
        if(i==1){
            return new ResultMessage(true,"添加成功");
        }else{
            return new ResultMessage(false,"添加失败");
        }
    }


}
