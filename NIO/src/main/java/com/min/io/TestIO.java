package com.min.io;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import sun.net.idn.Punycode;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/3/10 11:11
 */
public class TestIO {

    @Test
    public void test(){
        Runtime runtime = Runtime.getRuntime();
        Process process;
        BufferedReader br = null;
        BufferedWriter wr = null;
        try {
            process = runtime.exec("E:\\Projects\\GIT\\Python\\dist\\sleep.exe");

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            wr = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

            String inline;
            while ((inline = br.readLine()) != null) {
                if (!inline.equals("")) {
                    inline = inline.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
//                    session.getBasicRemote().sendText(inline);  //返回给页面
                    if (inline.endsWith("发布到服务器?[Y/N]:")) {
                        wr.write("y");                          //自动输入y
                        wr.newLine();
                        wr.flush();
//                        session.getBasicRemote().sendText("y");
                    }
                } else {
//                    session.getBasicRemote().sendText("\n");    //换行
                    System.out.println("换行");
                }
            }
            br.close();
            br = new BufferedReader(new InputStreamReader(process.getErrorStream()));    //错误信息
            while ((inline = br.readLine()) != null) {
                if (!inline.equals("")){
//                    session.getBasicRemote().sendText("<font color='red'>" + inline + "</font>");
                    System.out.println("错误消息为：" + inline);
                }else{
//                    session.getBasicRemote().sendText("\n");
                    System.out.println("\n");
                }

            }
        } catch (Exception e) {
//            session.getBasicRemote().sendText("<font color='red'>" + e.getMessage() + "</font>");
            System.out.println("错误消息为：" + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                    if (wr != null)
                        wr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            session.getBasicRemote().sendText("End.");
            System.out.println("End.");
        }
    }

    @Test
    public void runBuffers() throws InterruptedException {
        List<String> filePaths = Arrays.asList("C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\readme.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\123.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\1.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\2.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\3.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\4.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\5.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\6.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\7.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\8.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\9.txt",
                "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\10.txt");
        CountDownLatch countDownLatch = new CountDownLatch(filePaths.size());
        for (int i = 0; i< filePaths.size(); i ++ ){
            String filepath = filePaths.get(i);
            starOneThread(filepath);
        }
        countDownLatch.await();
    }

    public void starOneThread(String filepath){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName() ;
                bufferWrite(filepath, name);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                bufferReade(filepath);
            }
        }).start();
    }



    public void bufferWrite(String filepath, String name){
        //创建一个字符缓冲输出流对象
        int i =0;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filepath)) ){
            while (i<100){
                //写数据
                bw.write(name);
                bw.write("  hello  ");
                bw.write("world  " + i);
                i++ ;
                bw.write("\n");
                //刷新流
                bw.flush();
//                Thread.sleep(50);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("write finish");
    }

    public void  bufferReade(String filepath){
        File file = new File(filepath);
        BufferedReader br = null;
        BufferedWriter wr = null;
        String inline = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while (true) {
                inline = br.readLine();
                if(StringUtils.isNoneEmpty(inline)){
                    if(inline.equals("end"))
                        break;
                    System.out.println(inline);
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println("错误消息为：" + e.getMessage());
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        }
    }
}
