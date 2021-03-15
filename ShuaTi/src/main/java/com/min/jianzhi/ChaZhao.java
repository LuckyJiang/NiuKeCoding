package com.min.jianzhi;

import org.junit.Test;
import org.junit.platform.commons.logging.LoggerFactory;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/9 17:04
 */
public class ChaZhao {

    @Test
    public void test1(){
        int array[] = {6501,6828,6963,7036,7422,7674,8146,8468,
                8704,8717,9170,9359,9719,9895,9896,9913,9962,154,
                293,334,492,1323,1479,1539,1727,1870,1943,2383,2392,
                2996,3282,3812,3903,4465,4605,4665,4772,4828,5142,5437,5448,5668,5706,5725,6300,6335};
        System.out.println(minNumberInRotateArray(array));
    }

    /**
     * JZ6： 旋转数组的最小数字
     * 题目：
     *      把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     *      输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     *      例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     *      NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * 思路：
     *      采用二分查找算法
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0)
            return 0;
        int left = 0, right = array.length -1, middle = -1;
        while (left < right){
            middle = (left + right) /2;
            if(array[left] < array[right])
                return array[left];
            if(array[middle] < array[right]){
                right = middle;
            }else if(array[middle] > array[right]){
                left = middle + 1;
            }else{
                right --;
            }
        }

        return array[left];
    }

}
