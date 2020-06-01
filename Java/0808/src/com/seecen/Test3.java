package com.seecen;

import java.io.*;

/**
 * @author ${林锋鹏}
 * @Title: Test3
 * @ProjectName Java
 * @date 2019/8/8 14:27
 */
public class Test3 {
    public static void main(String[] args) throws Exception {
        File file1= new File("H:book.txt");
        File file2 =new File("H:b.txt");
        if(!file2.exists()){
            file2.createNewFile();
        }
        Reader reader = new FileReader(file1);
        Writer writer = new FileWriter(file2);
        try(
        BufferedReader br = new BufferedReader(reader, 1024 * 512);
        BufferedWriter bw = new BufferedWriter(writer,1024 * 512);
        PrintWriter pw = new PrintWriter(bw);
        ){
            while(true){
                String str = br.readLine();
                if(str==null){
                  break;
                }
                pw.println(str);

         //       bw.append(str);
            }

        }

    }
}
