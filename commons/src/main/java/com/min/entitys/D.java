package com.min.entitys;

import com.min.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/12/3 16:46
 */
//@Component
public class D extends B {
    public void testD(){
        System.out.println("我是D，我继承B");
    }
}
