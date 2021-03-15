package com.min.controller;

import com.min.exception.entity.GlobalException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jxm
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = Exception.class)
    public Msg errorHandler(Exception e){
        if(e instanceof GlobalException){
            return new Msg(-1, e.getMessage(), null);
        }
        return new Msg(-1, "unknown error", null);
    }

    public static class Msg<T>{
        /**
         * code>=0 代表success; msg包含信息，data有效
         * code< 代表异常。此时msg包含错误信息；data无效
         */
        private Integer code;
        private String msg;
        private T data;

        public Msg() {
            this.code = 0;
            this.msg = "success";
            this.data = null;
        }

        public Msg(Integer code, String msg, T data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }


        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }

}
