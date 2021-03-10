package com.min;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/12/3 16:38
 */
@Profile("aims070")
@Component
public class B implements A {
    @Override
    public void test() {
        System.out.println("我是B,实现了接口A");
    }
    
    @Override
    public void test001() {
        System.out.println("测试如何废弃一个接口");
    }

    public void testB() {
        System.out.println("我是B,调用我自己的方法");
    }

    @Override
    public void a() {
        System.out.println("b.a");
    }

    @Override
    public void b() {
        System.out.println("b.b");
    }
}
