package com.min.nio;

import java.nio.ByteBuffer;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/3/7 14:25
 */
public class TestNIO {
    /**
     * 一、定义一个缓冲区 ：allocate()
     *
     * 二、缓冲区存取数据的两个核心方法：
     * put():存入数据到缓冲区中
     * get():获取缓冲区中的数据
     * flip():自动切换读取数据模式，要么为读模式，要么为写模式
     * rewind():可重复度数据
     * clear():清空缓冲区，但缓冲区中的数据依然存在，但是处于“被遗忘”状态
     *
     * 三、缓冲区中的四个核心属性
     * capacity: 容量，表示缓冲区中最大存储数据的容量。
     * limit:    缓冲区中，可以操作数据的大小。（limit后面的数据不能进行读写）
     * position： 缓冲区招工正在操作数据的位置, 默认为0；
     * mark:     标记，表示当前position的位置，可以通过reset（）恢复到mark的位置
     *
     * 规律：position <= limit <= capacity
     *
     *
     */

    public static void main(String[] args) {
        //1.定义一个缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(1024);


    }
}
