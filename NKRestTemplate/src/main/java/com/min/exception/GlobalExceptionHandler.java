package com.min.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用于处理所有controller异常的情况
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOutIndex(Exception e){
        System.out.println(GlobalExceptionHandler.class.getName());
        return "GlobalExceptionHandler: error";
    }
}
