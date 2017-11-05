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
public class Tusercard {
    /**
     * 主键
     */
    private String id;

    /**
     * 关联用户
     */
    private String userid;

    /**
     * 银行卡号
     */
    private String cardnum;

    /**
     * 所属银行卡
     */
    private String cardbank;

    /**
     * 银行编号
     */
    private String banknum;

    /**
     * 所属支行
     */
    private String branch;

    /**
     * 添加时间
     */
    private Date intime;


}