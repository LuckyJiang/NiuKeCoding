package com.min.exception.entity;

/**
 * @author jxm
 * 全局的异常
 */
public class GlobalException extends RuntimeException {

    private Integer code;

    public GlobalException(Integer code) {
        this.code = code;
    }

    public GlobalException(final String message) {
        super(message);
    }

    public GlobalException(String message, Integer code) {
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
