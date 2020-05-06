package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.CouBean;
import com.jiubo.buildstore.bean.CounselorCommentBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface CounselorCommentService extends IService<CounselorCommentBean> {

    public CouBean getCounselorByBid(CounselorCommentBean counselorCommentBean);

    public CounselorCommentBean updateNumById(CounselorCommentBean counselorCommentBean) throws MessageException;

    public static String sendHttpContent(String httpURL, String msg) {
        HttpURLConnection connection = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL address_url = new URL(httpURL);
            connection = (HttpURLConnection) address_url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", msg.length() + "");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(msg.getBytes("UTF-8"));
            os.flush();
            os.close();

            int code = connection.getResponseCode();

            if (code == 200)
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                    String strCurrentLine;
                    while ((strCurrentLine = reader.readLine()) != null) {
                        stringBuffer.append(strCurrentLine);
                    }
                    reader.close();
                } catch (IOException localIOException) {
                    localIOException.printStackTrace();
                }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    public Page<CounselorCommentBean> getCounselorByPage(CounselorCommentBean counselorCommentBean);

    public void updateComById(CounselorCommentBean counselorCommentBean, MultipartFile[] picImg) throws Exception;

    public void addCom(CounselorCommentBean counselorCommentBean, MultipartFile[] picImg) throws Exception;
}
