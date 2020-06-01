package com.seecen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ${林锋鹏}
 * @Title: Demo2
 * @ProjectName Java
 * @date 2019/8/8 11:03
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("H:" + File.separator + "a.txt");
        if(!file.exists()){
            file.createNewFile();
        }

            OutputStream os = new FileOutputStream(file);
            byte[] bytes= "思成".getBytes();
            System.out.println(bytes.length);
            os.write(bytes);

        }


    }

