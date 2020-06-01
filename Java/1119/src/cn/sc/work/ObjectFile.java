package cn.sc.work;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ${林锋鹏}
 * @Title: ObjectFile
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 18:32
 */
public class ObjectFile {
    //序列化进文件
    public void  objectOutputFile(List list){
        File file = new File("G:a.txt");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            oos.writeObject(list);
            oos.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    //反序列化文件
    public ArrayList objectInputFile(){
        ArrayList<User> list=null;
        File file = new File("G:a.txt");
        try (ObjectInputStream oip = new ObjectInputStream(new FileInputStream(file))) {
           list = ( ArrayList<User>)oip.readObject();

        }catch (Exception  e){
            e.printStackTrace();
        }
        return  list;
    }
}
