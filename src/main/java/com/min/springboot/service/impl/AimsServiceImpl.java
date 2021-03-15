package com.min.springboot.service.impl;

import com.min.springboot.service.AimsService;
import org.springframework.stereotype.Service;


/**
 * @author jxm
 */
@Service
public class AimsServiceImpl  implements AimsService {


    @Override
    public void aimstest() {
        System.out.println("AimsServiceImpl---异常前");
//        try{
            int num = 10/0;
//        }catch (Exception e){
//            System.out.println("-----------异常--------");
////            throw e;
//        }
        System.out.println("AimsServiceImpl---异常后");

    }

}
