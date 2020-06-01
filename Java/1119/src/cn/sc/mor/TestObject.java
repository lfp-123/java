package cn.sc.mor;

import org.junit.Test;

import java.io.*;

/**
 * @author ${林锋鹏}
 * @Title: TestObject
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 14:45
 */
public class TestObject  {
    public static void main(String[] args) throws IOException {


        // 1.序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("G:a.txt")));
        //2.使用方法实现序列化，writeObject
        User user = new User(1,"lin","123","超级管理员");
        oos.writeObject(user);
        oos.close();


    }
    @Test
    public void testObjectInputstream() throws IOException,ClassNotFoundException{
        //1.创建流
        FileInputStream fis = new FileInputStream("G:a.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        //2.读取写入文件 readObject()
        User o = (User)ois.readObject();
        System.out.println(o);

        ois.close();
        fis.close();
    }
}
