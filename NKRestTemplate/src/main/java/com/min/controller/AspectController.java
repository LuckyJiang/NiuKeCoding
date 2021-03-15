package com.min.controller;

import com.min.service.AimsService;
import com.min.service.AspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/17 17:08
 */

@RestController
@ResponseBody
public class AspectController {

    @Autowired
    private AspectService aspectService;

    @Autowired
    private AimsService aimsService;

    @GetMapping("/aspect")
    public String test(){
        aspectService.aspectException();
        return "success";
    }

    @GetMapping("/test")
    public String test1(){
//        aimsService.aimstest();
        return "success";
    }
}
