package com.min.writeFileAndReadChange;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/2/24 17:16
 */

import java.io.File;
import java.util.Date;

/**
 * 每1秒钟一次，判断指定的某个目录下的某个文件是否被修改，如果被修改， 就在Console上打印出“File changed…”
 *
 * 这题调用了File类的lastModified()方法获得文件最后一次修改的时间；
 *
 * Thread的sleep(1000)方法让该线程睡1秒
 */
public class JudgeChanged {

    private String infile;
    boolean flag = true;

    public JudgeChanged(String infile) {
        super();
        this.infile = infile;
    }

    public void run() {
        File file = new File(infile);
        while (flag) {                //一直监视
            try {
                long s1 = file.lastModified();
                Thread.sleep(1000);
                long s2 = file.lastModified();
                Date date2 = new Date(s2);
                if (s1 != s2) {        //判断一秒钟前后的最后修改时间是否相等
                    System.out.println("File changed-------");
                    System.out.println("修改时间为："+date2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        JudgeChanged jc = new JudgeChanged("C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\readme.txt");
        System.out.println("监听文件是否被修改·······");
        jc.run();
    }
}
