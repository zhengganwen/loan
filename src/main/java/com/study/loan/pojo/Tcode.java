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
public class Tcode {
    /**
     * 主键
     */
    private String id;

    /**
     * 手机号
     */
    private String userphone;


    /**
     * 验证码
     */
    private String vercode;

    /**
     * 添加时间
     */
    private Date intime;

    @Override
    public String toString() {
        return "Tcode{" +
                "id='" + id + '\'' +
                ", userphone='" + userphone + '\'' +
                ", vercode='" + vercode + '\'' +
                ", intime=" + intime +
                '}';
    }
}