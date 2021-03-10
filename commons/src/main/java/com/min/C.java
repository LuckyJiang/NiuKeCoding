package com.min;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/12/3 16:39
 */
@Profile("aims100")
@Component
public class C extends B {
    public void test1(){
        System.out.println("我是C，继承了B");
    }
    public void b(){
        System.out.println("c.b");
    }
}
