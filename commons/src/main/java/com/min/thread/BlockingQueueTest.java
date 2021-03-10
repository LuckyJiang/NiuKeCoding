package com.min.thread;

import java.util.concurrent.*;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/3/4 14:54
 */
public class BlockingQueueTest {

    public static void main(String[] args) {

//        syncQueue();
//
        arrayQueue();
//
//        linkedQueue();

    }



    /**
     * SynchronousQueue
     */
    private static void syncQueue() {
        System.out.println("\n =======SynchronousQueue====== \n");
        Executor executors = new ThreadPoolExecutor(
                2, 3, 30, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new RejectHandler());
        execute(executors);
    }


    /**
     * ArrayBlockingQueue
     */
    private static void arrayQueue() {
        System.out.println("\n =======ArrayBlockingQueue====== \n");
        Executor executors = new ThreadPoolExecutor(
                2, 3, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                new RejectHandler());
        execute(executors);
    }

    /**
     * LinkedBlockingQueue
     */
    private static void linkedQueue() {
        System.out.println("\n =======LinkedBlockingQueue====== \n");
        Executor executors = new ThreadPoolExecutor(
                2, 3, 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new RejectHandler());
        execute(executors);
    }

    private static void execute(Executor executors) {
        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread((Runnable) () -> {
                String name = Thread.currentThread().getName();
                System.out.println( name + "   is running... ");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println( name + "   run over... ");
            }, Integer.toString(i));
            executors.execute(thread);
        }
    }
}
