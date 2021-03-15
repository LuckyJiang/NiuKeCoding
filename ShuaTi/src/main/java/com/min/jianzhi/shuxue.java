package com.min.jianzhi;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/3 16:10
 * 47  48
 */
@SpringBootTest
public class shuxue {

    @Test
    public void test01(){
        System.out.println(Add(-1,1));
    }

    /**
     * JZ48: 位运算
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1,int num2) {
        int temp;

        while(num2 !=0){
            temp = (num1 & num2) <<1;
            num1 = num1 ^num2;
            num2 = temp;
        }
        return num1;
    }

    /**
     * JZ47: 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {

        boolean x = n > 1 && (n += Sum_Solution(n-1)) ==1;
        return n;
    }

}
