package com.seecen.ssm.util;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ${林锋鹏}
 * @Title: UploadUtil
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/1/30 10:58
 */
public class UploadUtil {
    public static String upload(MultipartFile imageFile, HttpServletRequest req){
        //a.获取文件名
        String oldName = imageFile.getOriginalFilename();
        //获取后缀名
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        //随机产生文件名，产生文件名
        String newName = UUID.randomUUID().toString()+suffix;
        //将文件保存到指定目录
        String realPath = req.getServletContext().getRealPath("/upload");
        String path=  realPath+File.separator+newName;

        File file = new File(path);
        if(!file.exists()){
            System.out.println("文件不存在！已创建新的目录！");
            file.mkdirs();
        }
        try {
            imageFile.transferTo(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        return newName;
    }
}
