package cn.sc.mor;

import java.io.*;

/**
 * @author ${林锋鹏}
 * @Title: TestCharCopy
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 10:55
 */
public class TestCharCopy {
    public static void main(String[] args) throws  Exception{
      File file = new File("G:a.txt");
       File file1 = new File("G:b.txt");
       
//        FileReader fr = new FileReader(file);
//        FileWriter fw = new FileWriter(file1);
//        char[] chars = new char[1024];
//        int len = 0;
//        while ((len = fr.read(chars))!=-1){
//           fw.write(chars,0,len);
//        }
//        fr.close();
//        fw.close();
        /*
        *通过缓冲流实现文本的复制*/
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
        //br.readLine读取的数据的内容，返回的是内容，返回的=null说明结束
        String mess="";
        while ((mess=br.readLine())!=null){
            bw.write(mess);
        }

        br.close();
        bw.close();



    }
}
