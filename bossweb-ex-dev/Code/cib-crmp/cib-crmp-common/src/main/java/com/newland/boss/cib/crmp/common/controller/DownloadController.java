package com.newland.boss.cib.crmp.common.controller;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

@RestController
public class DownloadController {

    private static final Logger LOGGER = LogManager.getLogger(DownloadController.class);
    private static final String UTF_8 = "utf-8";
    private static final String ISO_8859_1 = "iso-8859-1";

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public ResponseEntity<byte[]> ratedCdrDownload(@RequestParam("fileName") String fileName) {

        HttpHeaders headers = new HttpHeaders();
        String downloadFileName;
        byte[] bytes = null;
        try {
            String dir = System.getProperty("user.dir");

            if (!(dir.contains("base_domain") || dir.contains("cib") || dir.contains("eclipse")) || fileName.contains("../")) {
                throw new Exception("该路径不合法");
            }
            if (!fileName.endsWith(".xls") || !fileName.endsWith(".xlsx")) {
                throw new Exception("给文件类型不允许下载");
            }
            String path = dir + "/downloadTemp/";
            String ftpPath = dir + "/downloadFtpTemp/";
            fileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(path + fileName);
            downloadFileName = new String(fileName.getBytes(UTF_8), ISO_8859_1);
            headers.setContentDispositionFormData("attchment", downloadFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            bytes = FileUtils.readFileToByteArray(file);
            if (file.exists()) {
                if (fileName.startsWith("cdr") || fileName.startsWith("dma") || fileName.startsWith("网络专线")) {
                    copyFile(path + fileName, ftpPath + fileName);
                }
                boolean flag = file.delete();
                if (!flag) {
                    LOGGER.info("临时文件删除失败");
                }
            }
        } catch (Exception e) {
            LOGGER.error("downloadExcel", e);
        }
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

    }

    // 模板下载
    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    public ResponseEntity<byte[]> downloadRuleTemplate(@RequestParam("filePath") String filePath)
            throws Exception {

        String absoluteFileName = filePath;
        if (!new File(absoluteFileName).exists()) {
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();
            URL u = servletContext.getResource("/");
            String weblogicPath = u.getPath().replaceAll("%20", " ");
            absoluteFileName = weblogicPath + absoluteFileName;
            if (!new File(absoluteFileName).exists()) {
                absoluteFileName = absoluteFileName.replaceFirst("/", "");
            }
        }
        File file = new File(absoluteFileName);
        HttpHeaders headers = new HttpHeaders();
        String downloadFileName = file.getName();
        byte[] bytes = null;
        try {
            if (filePath.contains("../")) {
                throw new Exception("该路径不合法");
            }
            downloadFileName = new String(downloadFileName.getBytes(UTF_8), ISO_8859_1);
            headers.setContentDispositionFormData("attchment", downloadFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            LOGGER.debug(e.getMessage());
        }
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

    private void copyFile(String oldAddress, String newAddress) throws IOException {
        FileOutputStream output = null;
        try (FileInputStream input = new FileInputStream(oldAddress)) {
            // 创建excel文件
            File file = new File(newAddress);
            if (!file.getParentFile().exists()) {
                boolean mkdirflag = file.getParentFile().mkdirs();
                if (!mkdirflag) {
                    throw new IOException("文件目录创建失败");
                }
            }
            boolean flag = file.createNewFile();
            if (!flag) {
                throw new IOException("文件创建失败");
            }
            output = new FileOutputStream(newAddress);
            int in = input.read();
            while (in != -1) {
                output.write(in);
                in = input.read();
            }
        } catch (Exception e) {
            LOGGER.error("downloadExcel", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    LOGGER.error("output.close", e);
                }
            }
        }
    }
}
