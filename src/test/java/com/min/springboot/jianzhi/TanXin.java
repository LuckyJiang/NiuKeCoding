package com.min.springboot.jianzhi;

import org.junit.runner.RunWith;
import org.springframework.aop.RawTargetAccess;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/3 16:27
 * 8 9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TanXin {
    /**
     * JZ8: 跳台阶
     * 只能跳1个或者两个
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
       if(target ==1)
           return 1;
       if(target == 2)
           return 2;
       return JumpFloor(target-1) + JumpFloor(target -2);
    }

    /**
     * JZ9: 变态跳台阶
     * 可以跳1,2。。。n个
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        if(target ==1){
            return 1;
        }
        return 2 * JumpFloor(target-1);
    }

    /**
     * JZ47:剪绳子
     * @param target
     * @return
     */
    public int cutRope(int target) {
        return 0;
    }
}
