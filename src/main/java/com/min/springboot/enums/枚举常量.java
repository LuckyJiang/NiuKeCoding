package com.min.springboot.enums;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/17 16:12
 */
public enum 枚举常量 {

    YICODE(1),
    ERCODE(2),
    YIMESSAGE("星期一"),
    ERMESSAGE("星期二"),
    XINGQI_YI(001,"星期一"),
    XINGQI_ER(002,"星期二"),
    XINGQI_SAN(003,"星期三");

    private int code;
    private String massage;
    枚举常量(int code, String message){
        this.code=code;
        this.massage=message;
    }
    枚举常量(int code){
        this.code=code;
    }
    枚举常量(String message){
        this.massage=message;
    }

    public int getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }
}
