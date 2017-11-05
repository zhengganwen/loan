package com.study.loan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    /**
     * ‘主键’
     */
    private String id;

    /**
     * 关联的用户
     */
    @NotNull
    private String userid;

    /**
     * 关联用户名字
     */
    @NotNull
    private String uname;

    /**
     * 关联用户手机号
     */
    @NotNull
    private String uphone;

    /**
     * 1.配偶;2.父母;3.兄弟姐妹;4.朋友;5.同事;6其他
     */
    @NotNull
    private String relation;

    /**
     * 添加时间
     */
    private Date intime;

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", uname='" + uname + '\'' +
                ", uphone='" + uphone + '\'' +
                ", relation='" + relation + '\'' +
                ", intime=" + intime +
                '}';
    }
}