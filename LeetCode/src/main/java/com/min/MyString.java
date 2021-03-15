package com.min;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/1/15 9:13
 */
public class MyString {

    public static void main(String[] args) {
        MyString myString = new MyString();
        //125. 验证回文串
        myString.isPalindrome("Zeus was deified, saw Suez.");
    }

    /**
     * 125. 验证回文串
     * 方案1：双指针
     *      时间复杂度：
     *      空间复杂度：
     */
    public boolean isPalindrome(String s) {
        if(s.length()<=0)
            return true;
        int left = 0, right = s.length() - 1;
        while (left <= right){
            int asciiA = Integer.valueOf(s.charAt(left));
            int asciiB = Integer.valueOf(s.charAt(right));
            if(!isNumOrChar(asciiA)){
                left++;
                continue;
            }
            if(!isNumOrChar(asciiB)){
                right--;
                continue;
            }
            if(!isequals(asciiA, asciiB)){
                return false;
            }else{
                left++;
                right--;
            }

        }
        return true;

    }

    private boolean isequals(int asciiA, int asciiB) {
        if(asciiA==asciiB)
            return true;
        //一个是大写，一个是小写的情况
        if(asciiA>=65 &&  asciiB>=97 && (asciiA + 32)==asciiB){
            return true;
        }
        if(asciiA>=97 && asciiB>=65 && (asciiB+32)==asciiA)
            return true;
        return false;
    }

    public boolean isNumOrChar(int ascii){
        //将某个字符转换为ascii码
        //48, 65, 97
        if((ascii>=48&&ascii<=57)||(ascii>=65&&ascii<=90)||(ascii>=97&&ascii<=122))
            return true;
        return false;
    }
}
