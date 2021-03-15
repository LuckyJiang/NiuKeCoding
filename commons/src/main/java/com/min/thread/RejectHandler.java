package com.min.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/3/4 14:56
 */
public class RejectHandler implements  RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        String name = ((Thread)r).getName();

        System.out.println(name + "   is rejected");
    }
}
