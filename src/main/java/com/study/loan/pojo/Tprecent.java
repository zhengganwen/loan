package com.study.loan.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tprecent {
    /**
     * 主键
     */
    private String id;

    /**
     * 借款期限
     */
    private String  setdate;

    /**
     * 利率
     */
    private Double percent;

    /**
     * 添加时间
     */
    private Date intime;

    /**
     * 更新时间
     */
    private Date modtime;

    @Override
    public String toString() {
        return "Tprecent{" +
                "id='" + id + '\'' +
                ", percent=" + percent +
                ", intime=" + intime +
                ", modtime=" + modtime +
                '}';
    }
}