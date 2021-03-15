package com.min.writeFileAndReadChange.success1;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/2/24 16:53
 */

/**
 * nodes:
 *  1、一个抽象类A，实现某个接口B，不需要实现B中的方法
 *  2、一个普通类C，实现某个接口B，需要实现B中定义的方法
 */
public abstract class BaseFileListenner implements Runnable {
    public void listen(){
        String s = listenNewLine();
        doService(s);
    }

    public abstract String listenNewLine();

    public abstract void doService(String s);
}
