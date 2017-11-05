package com.study.loan.core;

import java.util.UUID;

public class uuid {
    public static String  getInstance(){
        return  UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }
}
