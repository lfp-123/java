package com.seecen.dd.cons;

        import com.seecen.dd.entity.MobileCard;
        import com.sun.imageio.spi.OutputStreamImageOutputStreamSpi;

        import java.io.*;
        import java.util.ArrayList;

/**
 * @author ${林锋鹏}
 * @Title: ObjectFile
 * @ProjectName 序列化方法
 * @date 2019/8/18 12:18
 */
public class ObjectFile {
    //序列化进文件
    public void  objectOutputFile(MobileCard card) {

        File file = new File("C:\\Users\\asus\\Desktop\\" +
                "sc190701\\学员\\林锋鹏\\Java\\DD5G\\src\\序列化.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            oos.writeObject(card);
            oos.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    //反序列化文件
    public ArrayList objectInputFile(){
        File file = new File("C:\\Users\\asus\\" +
                "Desktop\\sc190701\\学员\\林锋鹏\\Java\\DD5G\\src\\序列化.txt");
        ArrayList<MobileCard> list = new ArrayList<>();
        try (ObjectInputStream oip = new ObjectInputStream(new FileInputStream(file))) {
            MobileCard cards =  (MobileCard)oip.readObject();
            list.add(cards);
        }catch (Exception  e){
            e.printStackTrace();
        }
        return  list;
    }
}
