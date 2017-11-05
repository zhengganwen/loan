package com.study.loan.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author:Mr.Zheng
 * @Date:2017.11.2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String id;
    /**
     * 用户手机号
     */
    @NotNull(message = "手机号必填")
    private String userphone;
    /**
     * 用户密码
     */
    @NotNull(message = "密码必填")
    private String userpwd;
    /**
     * 验证码
     */
    @NotNull(message = "验证码必填")
    private String vercode;

}
