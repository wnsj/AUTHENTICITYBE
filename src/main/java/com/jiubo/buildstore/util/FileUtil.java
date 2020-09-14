package com.jiubo.buildstore.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.jiubo.buildstore.common.ImgPathConstant;

public class FileUtil {
	
	@Value("${buildStoreDir}")
    private static String buildStoreDir;
	
	public static String uploadFile(MultipartFile file,String s) throws IOException {
		 //原文件名
        String fileName = file.getOriginalFilename();
        fileName = fileName.substring(fileName.lastIndexOf("."));

//        File directory = new File("");// 参数为空
//        String path = directory.getCanonicalPath();
//        System.out.println("路径a：" + path);
//        String imgName = buildingBean.getBuildId().toString().concat(fileName);
        File dir = new File(s + ImgPathConstant.BUILD_PATH + 1 + "/" + "cs");
//        System.out.println("dir:" + dir.getPath());
        if (!dir.exists() && !dir.isDirectory()) dir.mkdirs();

        String name = UUID.randomUUID().toString().replace("-", "").concat(fileName);
//        System.out.println("name:" + name);

//        System.out.println("路径" + dir.getPath());
        String replace = dir.getPath().replace("\\", "/");
        String path = replace + "/" + name;

//        System.out.println("path:" + path);

        //读写文件
        if (!file.isEmpty()) {
            InputStream is = file.getInputStream();
            int len = 0;
            byte[] by = new byte[1024];
            OutputStream os = new FileOutputStream(path);
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            while ((len = bis.read(by)) != -1) {
                bos.write(by, 0, len);
                bos.flush();
            }
            if (null != bos)
                bos.close();
            if (null != bis)
                bis.close();
            if (null != os)
                os.close();
            if (null != is)
                is.close();
        }
		return null;
	}
	
	public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }
	
}
