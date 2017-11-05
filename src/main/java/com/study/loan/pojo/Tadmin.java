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
public class Tadmin {
    /**
     * 主键
     */
    private String id;

    /**
     * 账户名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 添加时间
     */
    private Date intime;

    @Override
    public String toString() {
        return "Tadmin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", intime=" + intime +
                '}';
    }
}