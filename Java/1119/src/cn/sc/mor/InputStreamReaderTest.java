package cn.sc.mor;

import java.io.*;

/**
 * @author ${林锋鹏}
 * @Title: InputStreamReaderTest
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 14:09
 */
public class InputStreamReaderTest {
    public static void main(String[] args) throws Exception{
        //字节转字符
        File file = new File("G:a.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        //isr其实就是字符流
        BufferedReader br = new BufferedReader(isr);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("G:b.txt"),true)));
        String mess="";
        while ((mess = br.readLine())!=null){
            bw.write(mess);
            bw.write("\n");
        }
        bw.close();
        br.close();
        isr.close();
        fis.close();

    }

}
