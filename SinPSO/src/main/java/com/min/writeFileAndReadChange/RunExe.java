package com.min.writeFileAndReadChange;

import java.awt.*;
import java.io.*;
import java.util.Arrays;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/3/1 17:33
 */
public class RunExe {

    /**
     * 不会阻塞进程的实行
     *     https://blog.csdn.net/zdavb/article/details/50791163
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /*for (int i = 0; i< 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //执行exe方案1：
//                        runExe(args);
                        //执行exe方案2：桌面打开exe的形式执行
                        Desktop.getDesktop().open(new File("E:\\Projects\\GIT\\Python\\dist\\sleep.exe"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
//            runExe(args);   //会阻塞，一个个执行
        }*/

        /**
         * 调用exe的时候，后面是否会阻塞
         */
        //调用exe的时候，后面被阻塞了
        runExe(args);
        for (int i = 0; i < 10; i++) {
            System.out.println("验证调用exe是否会引起阻塞" +i);
        }


    }

    private static void runExe(String[] args) throws IOException {

        System.out.printf("Output of running %s is:", Arrays.toString(args));
        Process process = new ProcessBuilder("E:\\Projects\\GIT\\Python\\dist\\sleep.exe").start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;

//        System.out.printf("Output of running %s is:", Arrays.toString(args));

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }


}
