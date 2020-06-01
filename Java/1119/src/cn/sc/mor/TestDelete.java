package cn.sc.mor;

import java.io.File;

/**
 * @author ${林锋鹏}
 * @Title: TestDelete
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/11/19 9:28
 */
public class TestDelete {

    /*File 类：泛指文件或者目录
    如何删除目录，可以采用递归删除实现文件目录的删除，方法中再次调用自己
     */
    public static void main(String[] args) {
        File file = new File("F:\\a");
        //通过无限次的调用对文件的删除
        deleteFile(file);

    }
    public static void deleteFile(File file){
        //确定了目录中没有子目录和没有子文件
        File[] files = file.listFiles();
        if(files!=null&&files.length>0){
            for (File fs :
                    files) {
                deleteFile(fs);
            }
        }else {
            //该file没有子目录
            boolean b = file.delete();
           if(b) System.out.println("文件删除成功："+file.getAbsolutePath());
        }
        boolean b= file.delete();
        if(b) System.out.println("文件删除成功："+file.getAbsolutePath());
    }
}
