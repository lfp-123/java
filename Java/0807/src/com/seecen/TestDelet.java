package com.seecen;

import java.io.File;

/**
 * @author ${林锋鹏}
 * @Title: TestDelet
 * @ProjectName Java
 * @date 2019/8/7 16:01
 */
public class TestDelet {
    public static void main(String[] args) {
        String pathname = "H:/c/a/a/a/a";
        File a = new File("H:/c");

//        delet(a);
//        System.out.println(delet(a))
        deleteFiles(a);


    }
    public static boolean delet(File file){
        if(file.isFile()){
            file.delete();
            return true;
        }
        if(file.isDirectory()){
            if(file.list()!=null){
                File[] files = file.listFiles();
                for (File a: files) {
                    delet(a);
                }
            }
            file.delete();
        }
            return true;

    }
    public static boolean deleteFiles(File file) {
        if(file.isFile())  {
            file.delete();
            return true;
        }
        if(file.isDirectory()) {
            if(file.list() != null)  {
                File[] files = file.listFiles();
                for (File childrenFile : files) {
                    deleteFiles(childrenFile);
                }
            }
            file.delete();
        }

        return true;
    }
}
