package com.study.loan.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Mr.Zheng
 * @Description
 * @Date 2017/11/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Picture {
    /**
     * 主键
     */
    private  String id;
    /**
     * 链接地址
     */
    private String href;
    /**
     * 照片地址
     */
    private String path;
    /**
     * 添加时间
     */
    private Date intime;
}
