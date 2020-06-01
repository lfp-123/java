package com.homeworks;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
/**
 * @author ${林锋鹏}
 * @Title: Test
 * @ProjectName Java
 * @date 2019/8/7 17:45‘
 */
public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("请输入你的路径：");
        Scanner scanner = new Scanner(System.in);
         String url = scanner.next();
       //  String url = "H:\\bb";
        File file = new File(url);
        fun(file);
    }
    public  static  void fun(File file) throws  Exception {
        File[] files = file.listFiles();
        for (File file1:files){
            if(file1.isFile()){
                FileReader fileReader = new FileReader(file1);
                char[] aa = new char[20];
                while (fileReader.read(aa) != -1) {
                    String str = new String(aa);
                    System.out.print("该文件夹的名字："+file1.getName()+"\n"+str);
                    System .out.println();
                }
            }
            if(file1.isDirectory()){
                fun(file1);
           }
        }
    }
}
