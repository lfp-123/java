package com.seecen;

import java.io.*;

/**
 * @author ${林锋鹏}
 * @Title: Test2
 * @ProjectName Java
 * @date 2019/8/8 14:11
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        File destFile = new File("H:" + File.separator + "A.txt");
        if(!destFile.exists()) destFile.createNewFile();
        try(InputStream is = new FileInputStream("H:book.txt");
            //输入缓冲区
            InputStream bis = new BufferedInputStream(is, 1024 * 1024 >> 1);
            //输出缓冲区
            OutputStream os = new FileOutputStream(destFile);

            OutputStream bos = new BufferedOutputStream(os, 1024 * 1024 >> 1);) {
            byte[] datas = new byte[1024];
            int len = 0;
            while((len = bis.read(datas)) != -1) {
                bos.write(datas, 0, len);
            }
            bos.flush();
        }
    }
}
