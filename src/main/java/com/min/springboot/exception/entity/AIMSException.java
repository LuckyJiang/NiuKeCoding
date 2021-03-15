package com.min.springboot.exception.entity;

/**
 * @author jxm
 * aims连接异常  todo 删除
 */
public class AIMSException extends RuntimeException{

    private Integer code;

    public AIMSException(){

    }

    public AIMSException(Integer code) {
        this.code = code;
    }

    public AIMSException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
