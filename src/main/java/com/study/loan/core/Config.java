package com.study.loan.core;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description
 * @author:Mr.Zheng
 * @Date:2017.10.28
 */
public class Config {
    /**
     * 用户状态
     */
    //注册
    public final static String  user_state_R = "1";
    //实名认证通过
    public final static String  user_state_Y = "2";
    //实名认证不通过
    public final static String  user_state_N ="3";
    //本人判断通过
    public final static String  user_state_D ="4";

    /** 存储照片所保存的路径 */
    private static String DIR;
    static {
        DIR = Config.class.getResource("").getPath();
        DIR = DIR.substring(0, DIR.indexOf("/WEB-INF"));
    }

    /**
     * EXP目录
     */
    public final static String EXP_DIR = DIR + File.separator + "exp";

    public static  String  CompareDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        // 3分钟之前的时间
        beforeTime.add(Calendar.MINUTE, -3);
        Date beforeD = beforeTime.getTime();
        return  sdf.format(beforeD);
    }
}
