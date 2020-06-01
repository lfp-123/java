package cn.sc.mor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author ${林锋鹏}
 * @Title: TestCopy
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 10:08
 */
public class TestCopy {
    public static void main(String[] args) throws Exception {
        /*实现文件赋值
        先读取。后写入
         */
        byte[] bytes = new byte[1024];
        File file = new File("D:a.png");
        File files = new File("D:lxx.png");
        FileInputStream fs = new FileInputStream(file);
        FileOutputStream os = new FileOutputStream(files);
        long begin = System.currentTimeMillis();
        int leng = 0; //不能不每次读一整个数组，要取到每次读取内容的长度
        while ((leng = fs.read(bytes))!=-1) //每循环一次读一个byte数组的长度，直到读取结束
        {
            os.write(bytes,0,leng);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制文件成功，使用："+(end-begin)+"毫秒");
        fs.close();
        os.close();
    }
}
