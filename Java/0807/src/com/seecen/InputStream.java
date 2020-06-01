package com.seecen;

import java.io.*;

/**
 * @author ${林锋鹏}
 * @Title: InputStream
 * @ProjectName Java
 * @date 2019/8/7 16:48
 */
public class InputStream {
    public static void main(String[] args) throws  Exception {
        File file = new File("H:a.txt");

        try(FileReader fileInputStream = new FileReader(file)) {
//            for (int i = 0; i <file.length() ; i++) {
//                int read = fileInputStream.read();
//                System.out.print((char) read);
//            }
//            FileReader fileInputStream = new FileReader(file);
//            FileInputStream fileInputStream = new FileInputStream(file)
            char[] aa = new char[1];

            while (fileInputStream.read(aa)!=-1){
                String str = new String(aa);
                System.out.print(str);

            }
//            char[] chars =new char[5];
//            int read = fileInputStream.read();
//            while (true){
//                if(read ==-1){
//                    break;
//                }
//                System.out.println();
//            }



        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
