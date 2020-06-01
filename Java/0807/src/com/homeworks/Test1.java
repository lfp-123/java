package com.homeworks;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

/**
 * @author ${林锋鹏}
 * @Title: Test1
 * @ProjectName Java
 * @date 2019/8/8 9:53
 */
public class Test1 {
    public static void main(String[] args) {
        File file = new File("d:" + File.separator + "a");
        StringBuilder sb = new StringBuilder();
        readText(file, sb);
        // 输出结果
        System.out.println(sb);
    }
    public static void readText(File file, StringBuilder sb) {
        if (file.isFile()) {
            try (Reader reader = new FileReader(file)) {
                sb.append(file.getName() + "的内容如下:\r\n\t");
                char[] datas = new char[10];
                int len = 0;
                while ((len = reader.read(datas)) != -1) {
                    String str = new String(datas, 0, len);
                    sb.append(str);
                }
                sb.append("\r\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (file.isDirectory()) {
            // new的是这个接口的匿名实现类的对象
            /*File[] files = file.listFiles(new FilenameFilter(){
                public boolean accept(File dir, String name) {
                    // 如果遍历到的这个又是个目录,return true代表要
                    File childrenFile = new File(dir, name);
                    return childrenFile.isDirectory() || name.endsWith(".txt");
                }
                "
            });*/
            File[] files = file.listFiles((dir, name) -> new File(dir, name).isDirectory() || name.endsWith(".txt"));
            for (File f : files) {
                readText(f, sb);
            }
        }
    }
}
