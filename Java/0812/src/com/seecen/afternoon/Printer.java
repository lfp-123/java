package com.seecen.afternoon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: Printer
 * @ProjectName Java
 * @date 2019/8/12 16:40
 */
public class Printer {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;


    public void printName() {

       File file = new File("H:" + File.separator +name + ".txt");

       try( FileOutputStream os = new FileOutputStream(file,true)) {
           if (!file.exists()) {
               file.createNewFile();
           }
           for (int i = 0; i < name.length(); i++) {
               os.write(name.charAt(i));
           }
           os.write("\r\n".getBytes());
       }catch (Exception e){
           e.printStackTrace();
       }
   }

}
