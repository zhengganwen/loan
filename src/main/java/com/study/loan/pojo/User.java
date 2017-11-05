package com.study.loan.pojo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * 主键
     */

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户手机号
     */
    private String userphone;

    /**
     * 用户密码
     */
    private String userpwd;

    /**
     * 用户身份证号码
     */
    private String usercardnumber;

    /**
     * 身份证正面照片
     */
    private String frontpicture;

    /**
     * 身份证背面照片
     */
    private String backpicture;

    /**
     * 本人自拍照片
     */
    private String truepicture;

    /**
     * 1.用户已注册;2.用户已实名认证3.实名认证不通过;4.用户已删除
     */
    private String state;

    /**
     * 注册时间
     */
    private Date intime;

    /**
     * 更新时间
     */
    private Date modtime;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", userphone='" + userphone + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", usercardnumber='" + usercardnumber + '\'' +
                ", frontpicture='" + frontpicture + '\'' +
                ", backpicture='" + backpicture + '\'' +
                ", truepicture='" + truepicture + '\'' +
                ", state='" + state + '\'' +
                ", intime=" + intime +
                ", modtime=" + modtime +
                '}';
    }
}