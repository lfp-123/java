package cn.sc.mor;

import java.io.*;

/**
 * @author ${林锋鹏}
 * @Title: BufferTest
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 10:38
 */
public class BufferTest {
    public static void main(String[] args) throws Exception {
        BufferedInputStream bus = new BufferedInputStream(new FileInputStream(new File("D:a.png")));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("F:lax.png")));
        byte[] bytes = new byte[1024];
        int len = 0;
        //read方法加参数。
        while ((len = bus.read(bytes))!=-1){
            bos.write(bytes,0,len);
        }
        bus.close();
        bos.close();
    }

}
