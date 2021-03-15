package com.min.springboot.service;

import org.springframework.stereotype.Service;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/17 17:02
 */
@Service
public class AspectService {

    public void aspectException(){
        System.out.println("AspectService---异常前");
        try{
            int num = 10/0;
        }catch (Exception e){
            throw e;
        }
        System.out.println("AspectService---异常后");
    }
}
