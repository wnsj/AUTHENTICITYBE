package com.jiubo.buildstore.controller;


import com.jiubo.buildstore.Exception.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
@Slf4j
@Controller
@RequestMapping("/fileController")
public class FileController {
    @Value("${buildStoreDir}")
    private String buildStoreDir;
    //获取图片、视频、音频
    @RequestMapping("/getFile")
    public void getFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getParameter("path");
        if (StringUtils.isBlank(path)) throw new MessageException("路径为空!");
        if (!path.toUpperCase().startsWith(buildStoreDir.toUpperCase())) throw new MessageException("无权限访问!");
        File file = new File(path);
        outputFile(file, response);
    }

    private void outputFile(File file, HttpServletResponse response) throws Exception {
        try {
            if (file == null || !file.exists()) throw new MessageException("文件不存在!");
            ServletOutputStream outputStream = response.getOutputStream();
            //String contentType = Files.probeContentType(file.toPath());
            //response.addHeader("Content-Type",contentType);
            //System.out.println(contentType);
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[8192];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            if (bis != null) bis.close();
            if (fis != null) fis.close();
        } catch (ClientAbortException c) {
            // System.out.println("此异常不处理。。。。");
        }
    }

    public void download(HttpServletRequest request, HttpServletResponse response, File proposeFile) {
        InputStream inputStream = null;
        BufferedOutputStream bufferOut = null;

        try {
            long fSize = proposeFile.length();
//            String printSize = getPrintSize(fSize);
//            String m = printSize.substring(0, printSize.lastIndexOf("."));
//            int i = Integer.parseInt(m);
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(proposeFile.getName(), "UTF-8"));
            response.setHeader("Accept-Ranges", "bytes");
            long pos = 0L;
            long last = fSize - 1L;
            log.info("last{}",last);
            long sum = 0L;
            if (null != request.getHeader("Range")) {
                response.setStatus(206);

                try {
                    String numRang = request.getHeader("Range").replaceAll("bytes=", "");
                    String[] strRange = numRang.split("-");
                    if (strRange.length == 2) {
                        pos = Long.parseLong(strRange[0].trim());
                        last = Long.parseLong(strRange[1].trim());
                    } else {
                        pos = Long.parseLong(numRang.replaceAll("-", "").trim());
                    }
                } catch (NumberFormatException var28) {
                    System.out.println(request.getHeader("Range") + " is not Number!");
                    pos = 0L;
                }
            }

            long rangLength = last - pos + 1L;
            String contentRange = "bytes " + pos + "-" + last + "/" + fSize;
            response.setHeader("Content-Range", contentRange);
            response.addHeader("Content-Length", String.valueOf(rangLength));
            bufferOut = new BufferedOutputStream(response.getOutputStream());
            inputStream = new BufferedInputStream(new FileInputStream(proposeFile));
            inputStream.skip(pos);
            byte[] buffer = new byte[8192];
            boolean var18 = false;

            while (sum < rangLength) {
                int length = inputStream.read(buffer, 0, rangLength - sum <= (long) buffer.length ? (int) (rangLength - sum) : buffer.length);
                sum += (long) length;
                bufferOut.write(buffer, 0, length);
            }
        } catch (Throwable var29) {
            if (var29 instanceof ClientAbortException) {
                System.out.println("用户取消下载!");
            } else {
                System.out.println("下载文件失败....");
                var29.printStackTrace();
            }
        } finally {
            try {
                if (bufferOut != null) {
                    bufferOut.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException var27) {
                var27.printStackTrace();
            }

        }

    }

    public static String getPrintSize(long size) {
// 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
// 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
// 因为还没有到达要使用另一个单位的时候
// 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
// 因为如果以MB为单位的话，要保留最后1位小数，
// 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
// 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

}
