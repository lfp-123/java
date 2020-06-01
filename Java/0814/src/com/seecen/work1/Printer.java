package com.seecen.work1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Author 林锋鹏
 * @Date 2019/8/12 16:39
 * @Description
 */
public class Printer {

    String name;
    int age;
    int gender;

    public void printerName() {
        // 获取当前线程map里面保存的username
        String name = MyPrinterThread.threadLocal.get();
        File file = new File("d:" + File.separator + name + ".txt");
        try(OutputStream os = new FileOutputStream(file, true)) {
            if(!file.exists()) file.createNewFile();
            for (int i = 0; i < name.length(); i++) {
                os.write(name.charAt(i));
            }
            os.write("\r\n".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
