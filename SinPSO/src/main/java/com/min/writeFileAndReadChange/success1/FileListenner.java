package com.min.writeFileAndReadChange.success1;

import org.apache.commons.lang3.StringUtils;

import javax.swing.tree.TreeNode;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/2/24 16:56
 */

/**
 * 该方案，可以设置一个线程不停的去监听文件的修改，但是只有当文件写入并保存之后，才能获取到修改的变化
 */
public class FileListenner extends BaseFileListenner {

    /**
     * the last modify time of the file being listened
     */
    private long lastModifyTime = 0L;
    /**
     * the last modify position of the file
     */
    private long seekPosition = 0L;
    /**
     * the file path
     */
    private String filePath;
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String listenNewLine() {
        String result = null;
        try {
            File listenFile = new File(filePath);
            long l = listenFile.lastModified();
            if (listenFile.lastModified() > lastModifyTime) {
                lastModifyTime = listenFile.lastModified();
                RandomAccessFile file = new RandomAccessFile(listenFile, "r");
                file.seek(seekPosition);
                byte[] bytes = new byte[(int) (file.length() - seekPosition)];
                seekPosition = file.length();
                file.read(bytes);
                result = new String(bytes, StandardCharsets.UTF_8);
//                result = new String(bytes, "gbk");
            }

            //理想状态是边输入（输入的时候不需要保存）边读取，但是没有实现啊
            /*RandomAccessFile file = new RandomAccessFile(listenFile, "r");
            long currentFileLength = file.length();
            System.out.println("文件长度" + currentFileLength);
            if(currentFileLength > seekPosition){
                file.seek(seekPosition);
                byte[] bytes = new byte[(int) (file.length() - seekPosition)];
                seekPosition = currentFileLength;
                file.read(bytes);
                result = new String(bytes, StandardCharsets.UTF_8);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Carry out business operation on the changed contents of the monitored files, such as writing to Kafka
     */
    @Override
    public void doService(String s) {
        if (StringUtils.isNotBlank(s)) {
            System.err.print(s);
        }
    }

    @Override
    public void run() {
        while (true) {
            listen();
            try {
                TimeUnit.MILLISECONDS.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
// FileListenner fileListenner = new FileListenner();
// fileListenner.setFilePath("D:\\company\\ruigu_pro\\scala-ding\\src\\main\\scala\\dm\\test\\readme.txt");
// new Thread(fileListenner).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                writeToFile("C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\readme.txt");
            }
        }).start();

        //检测文件的变化，并读取内容
        FileListenner fileListenner1 = new FileListenner();
        fileListenner1.setFilePath("C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\readme.txt");
        new Thread(fileListenner1).start();
        //一直写入文件


        //判断文件是否写入完成
        /*String path = "C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\readme.txt";
        long oldLength;
        long newLength;
        //判断文件是否写入完成
        do {
            oldLength = new File("C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\readme.txt").length();
            System.out.println("File Uploading >>" + path + ": " + oldLength);
            Thread.sleep(3000);
            newLength = new File(oldPath).length();
            log.info("File Uploading >>" + path + ": " + newLength);
        } while (oldLength != newLength);
        log.info("File Uploading Success >> " + path);*/

        /*File fileRead = new File("C:\\Users\\pc-fl\\Desktop\\SimPSO\\TechnicalVerification\\readme.txt");
        if (!fileRead.exists())
            return;
        long old_length;
        int count = 0;
        do {
            old_length = fileRead.length();
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waitForWirtenCompleted: " + old_length + " " + fileRead.length());
        } while (old_length != fileRead.length());*/

    }

    private static void writeToFile(String filePath) {
        File file = new File(filePath);
        OutputStreamWriter osw = null;
        int i = 0;

        while (i<20){
            try (FileOutputStream fos = new FileOutputStream(file, true)) {
                osw = new OutputStreamWriter(fos, "utf-8");
                osw.write("测试内容--" + i++ +  "\r\n"); //写入内容
                osw.close();
                fos.close();
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
