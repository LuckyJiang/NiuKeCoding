package com.min.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** @ControllerAdvice
 * @ExceptionHandler
 *
 *
 * @author jxm
 * @version 1.0
 * @date 2020/6/17 16:19
 */
@Controller
@RequestMapping("/exception")
@ResponseBody
public class ExceptionController {

    @GetMapping("test01")
    public int test02(){
        //异常：
        int result = 1/0;
        return 12;
    }
}
