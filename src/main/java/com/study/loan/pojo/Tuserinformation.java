package com.study.loan.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tuserinformation {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户
     */
    private String userid;

    /**
     * 性别;1.男;2:女
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 1.本科以上2.本科3.高中4.高中以下
     */
    private String education;

    /**
     * 婚姻状况;1.未婚2.已婚3.离异4.其他
     */
    private String marital;

    /**
     * 工作单位
     */
    private String workplace;

    /**
     * 所属大行业
     */
    private String industryone;

    /**
     * 所属小行业
     */
    private String industrytwo;

    /**
     * 薪资待遇
     */
    private String salary;

    /**
     * 所在省份
     */
    private String provice;

    /**
     * 所在市
     */
    private String city;

    /**
     * 所在县或者区
     */
    private String county;

    /**
     * 所在具体地点
     */
    private String location;

    /**
     * 添加时间
     */
    private Date intime;

    /**
     * 更新时间
     */
    private Date modtime;


}