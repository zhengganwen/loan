package com.study.loan.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tapplication {
    /**
     * 主键
     */
    private String id;

    /**
     * 关联用户表id
     */
    private String userid;

    /**
     * 贷款金额
     */
    private Double money;

    /**
     * 贷款期限
     */
    private String getdate;

    /**
     * 贷款用途
     */
    private String userfor;

    /**
     * 贷款类型
     */
    private String type;

    /**
     * 贷款状态:1.已申请;2.已借款;3.已还款
     */
    private String state;

    /**
     * ’根据时间,利率生成还款金额‘
     */
    private Double returnmoney;

    /**
     * 添加时间
     */
    private Date intime;

    /**
     * 更新时间
     */
    private Date modtime;

    private User user;

    @Override
    public String toString() {
        return "Tapplication{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", money=" + money +
                ", getdate='" + getdate + '\'' +
                ", userfor='" + userfor + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", returnmoney=" + returnmoney +
                ", intime=" + intime +
                ", modtime=" + modtime +
                ", user=" + user +
                '}';
    }
}