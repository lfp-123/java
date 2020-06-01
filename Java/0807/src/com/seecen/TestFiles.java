package com.seecen;

import java.io.File;
import java.io.IOException;

/**
 * @author ${林锋鹏}
 * @Title: File
 * @ProjectName Java
 * @date 2019/8/7 15:11
 */
public class TestFiles  {

    public static void main(String[] args) throws IOException {

        String pathname = "H:\\a.txt";
        File file = new File(pathname);
        boolean newFile = file.createNewFile();
        System.out.println(newFile);
        File canonicalFile = file.getCanonicalFile();
        System.out.println(canonicalFile);
        long totalSpace = file.getTotalSpace();
        System.out.println(totalSpace/1024/1024/1024);
        File h = new File("H:\\周杰伦 - 全部专辑（无损或320K）");
//        String[] list = h.list();
//        for (String a:
//             list) {
//            System.out.println(a+" ");
//        }
        File[] files = h.listFiles();
     for (File a:files){
         System.out.println(a);
     }
        String name = "H:/a/b/bc";
        File filess = new File(name);
        file.mkdirs();

    }
}
