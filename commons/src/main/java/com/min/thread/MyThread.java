package com.min.thread;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/2/23 17:26
 */
public class MyThread {

    public static void main(String[] args) throws InterruptedException {
        runThreadByOrder();
    }

    /**
     * 按照顺序执行Thread
     */
    public static void runThreadByOrder() throws InterruptedException {
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(new MyRunable(i));
            thread.start();
            thread.join();
        }
    }

    /**
     * 按照顺序执行Thread
     */
    public static void runThreadByRandom(){
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(new MyRunable(i));
            thread.start();
        }
    }

    static class MyRunable implements Runnable{
        private int number;
        public MyRunable(int i){
            this.number = i;
        }

        @Override
        public void run() {
            System.out.println("当前线程的索引为---" + this.number);
        }
    }
}
