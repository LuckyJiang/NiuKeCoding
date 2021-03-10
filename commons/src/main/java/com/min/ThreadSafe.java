package com.min;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/11/24 10:06
 * 验证某个方式是否为线程安全的--线程不安全验证
 */
public class ThreadSafe {
    private static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    public static void main(String[] args) {

        stringThreadLocal.get();

        return;
    }

    /**
     * 1.单独使用SimpleDateFormat这个对象，多线程情况下是是线程不安全的
     */
    private static void method1() {
        while (true) {
            new Thread(() -> {
                String dateStr = f.format(new Date());
                try {
                    Date parseDate = f.parse(dateStr);
                    String dateStrCheck = f.format(parseDate);
                    boolean equals = dateStr.equals(dateStrCheck);
                    if (!equals) {
                        //当代码执行到这里的时候，说明线程是不安全的
                        System.out.println(equals + " " + dateStr + " " + dateStrCheck);
                    } else {
                        System.out.println(equals);
                    }
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }
    }

    /**
     * 2.借用ThreadLocal解决SimpleDateFormat线程不安全的问题，将这个变量放到一个线程中
     */
    private static void method2() {
        Set set = new HashSet();
        while (true) {
            new Thread(() -> {
                String dateStr = threadLocal.get().format(new Date());
                if(!set.contains(threadLocal.get())){
                    set.add(threadLocal.get());
                }
                try {
                    Date parseDate = threadLocal.get().parse(dateStr);
                    String dateStrCheck = threadLocal.get().format(parseDate);
                    boolean equals = dateStr.equals(dateStrCheck);
                    if (!equals) {
                        System.out.println(equals + " " + dateStr + " " + dateStrCheck);
                    } else {
                        System.out.println(equals);
                    }
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }
    }
}
