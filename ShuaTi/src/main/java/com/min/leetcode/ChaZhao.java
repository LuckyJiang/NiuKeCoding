package com.min.springboot.leetcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/3 14:22
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChaZhao {

    /**
     * LC138: 贪心算法：从两边开始找，此时宽度最大；那么如何选择移动那端的端点呢？
     * 思想：不管移动哪个点，宽度都会变小，此时如果想找maxarea只能是高度扩大，因此应该是抛弃高度较小的点，移动height较小的一端
     * @param height
     * @return
     */
    public int LC138 (int[] height) {
        int maxArea = 0, area = 0;
        int left = 0, right = height.length -1;
        while(left < right){

            if(height[left] <= height[right]){
                area = (right - left) * height[left];
                left ++;
            }else{
                area = (right - left) * height[right];
                right --;
            }
            if(area >= maxArea)
                maxArea = area;
        }

        return maxArea;
    }
    @Test
    public void LC134(){

    }

    /**
     *
     * @param num
     * @return
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(num.length < 3){
            return  result;
        }

        return null;
    }
}
