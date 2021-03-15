package com.min.jianzhi;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.*;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/7/9 10:31
 */
public class ZiFuChuan {

    /**
     * JZ43:左旋转字符串
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if(n > str.length())
            return str;
        StringBuffer buffer = new StringBuffer(str);
        String substring = buffer.substring(n) + buffer.substring(0, n);

        return substring;
    }

    public Queue<Character> q = new LinkedList<>();
    public Map<Character, Integer> charMap= new HashMap();

    public void Insert(char ch)
    {
        if(charMap.get(ch) == null){
            charMap.put(ch, 1);
            q.add(ch);
        }
        else
            charMap.put(ch, charMap.get(ch) + 1);
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce(){


        while (!q.isEmpty()){
            Character head = q.peek();
            Integer nums = charMap.get(head);
            if(nums ==1){
                return head;
            }
            q.remove();
        }

        return '#';
    }

    @Test
    public void test(){
        Insert('g');
        System.out.println(FirstAppearingOnce());
        Insert('o');
        System.out.println(FirstAppearingOnce());
        Insert('o');
        System.out.println(FirstAppearingOnce());
        Insert('g');
        System.out.println(FirstAppearingOnce());
        Insert('l');
        System.out.println(FirstAppearingOnce());
        Insert('e');
        System.out.println(FirstAppearingOnce());
    }
}
