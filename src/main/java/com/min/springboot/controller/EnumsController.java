package com.min.springboot.controller;

import com.min.springboot.enums.常量接口;
import com.min.springboot.enums.常量类;
import com.min.springboot.enums.接口常量;
import com.min.springboot.enums.枚举常量;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/17 16:17
 */
@RestController
public class EnumsController {

    @GetMapping("/enums")
    public List<String> list(){
        List<String> list = new ArrayList<>();
        list.add(Double.toString(常量接口.MATH_E));
        list.add(常量类.Success);
        list.add(枚举常量.XINGQI_YI.getCode() + 枚举常量.XINGQI_YI.getMassage());
        return list;
    }
}
