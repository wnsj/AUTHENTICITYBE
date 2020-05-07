package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.ArticleBean;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.dao.ArticleDao;
import com.jiubo.buildstore.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.jiubo.buildstore.util.DateUtils.*;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleBean> implements ArticleService {


    @Autowired
    private ArticleDao articleDao;

    @Value("${buildStoreDir}")
    private String buildStoreDir;
    @Override
    public List<ArticleBean> getArticleByType(ArticleBean articleBean) {
        List<ArticleBean> article = articleDao.getArticleByType(articleBean);
        if (null != article && article.size() > 0) {
            for (ArticleBean articleBean1 : article) {
                if (null != articleBean1.getCreateTime()) {
                    String formatDate = DateUtils.formatDate(articleBean1.getCreateTime(), "yyyy-MM-dd");
                    articleBean1.setCreateDate(formatDate);
                }
            }
        }
        return article;
    }

    @Override
    public Page<ArticleBean> getArticleByPage(ArticleBean articleBean) {
        Page<ArticleBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(articleBean.getCurrent()) ? 1L : Long.parseLong(articleBean.getCurrent()));
        page.setSize(StringUtils.isBlank(articleBean.getPageSize()) ? 10L : Long.parseLong(articleBean.getPageSize()));
        List<ArticleBean> articleByPage = articleDao.getArticleByPage(page, articleBean);
        if (null != articleByPage && articleByPage.size() > 0) {
            for (ArticleBean bean : articleByPage) {

                if (1==bean.getArticleType()) {
                    bean.setArticleTypeLabel("公司简介");
                } else if (2==bean.getArticleType()) {
                    bean.setArticleTypeLabel("发展历程");
                } else if (3==bean.getArticleType()) {
                    bean.setArticleTypeLabel("公司荣誉");
                }else if (4==bean.getArticleType()) {
                    bean.setArticleTypeLabel("企业团队");
                } else if(5==bean.getArticleType()){
                    bean.setArticleTypeLabel("企业文化");
                } else if (6==bean.getArticleType()) {
                    bean.setArticleTypeLabel("联系我们");
                } else if (7==bean.getArticleType()) {
                    bean.setArticleTypeLabel("服务声明");
                } else {
                    bean.setArticleTypeLabel("楼盘合作");
                }

                if (null != bean.getCreateTime()) {
                    String formatDate = DateUtils.formatDate(bean.getCreateTime(), "yyyy-MM-dd");
                    bean.setCreateDate(formatDate);
                }
            }
        }
        page.setRecords(articleByPage);
        return page;
    }



    @Override
    public List<Map<String, Object>> uploadFile(MultipartFile[] file) throws Exception {
        if (file == null || file.length <= 0) throw new MessageException("未接收到文件!");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (MultipartFile multipartFile : file) {
            //原文件名
            String fileName = multipartFile.getOriginalFilename();
            String srcName = fileName;
            //生成文件名
            fileName = fileName.substring(fileName.lastIndexOf("."));

            String path = buildStoreDir + ImgPathConstant.ARTICLE;
            File dir = new File(path);
            if (!dir.exists() && !dir.isDirectory()) dir.mkdirs();
            String replace = dir.getPath().replace("\\", "/");
            path = replace.concat("/").concat(UUID.randomUUID().toString().replace("-", "")).concat(fileName);
            System.out.println("路径" + path);
            Map<String, Object> map = new HashMap<>();
            map.put("srcName", srcName);
            map.put("fileUrl", ImgPathConstant.INTERFACE_PATH.concat(path));
            mapList.add(map);
            generateFile(multipartFile, path);
        }
        return mapList;
    }

    @Override
    public void insertArticle(ArticleBean articleBean){
        articleBean.setCreateTime(new Date());
        articleDao.insert(articleBean);
    }

    @Override
    public void updateArticle(ArticleBean articleBean) {
        articleDao.updateById(articleBean);
    }

    private void generateFile(MultipartFile multipartFile, String path) throws Exception {
        //读写文件
        if (!multipartFile.isEmpty()) {
            InputStream is = multipartFile.getInputStream();
            int len = 0;
            byte[] by = new byte[1024];
            OutputStream os = new FileOutputStream(path);
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            while ((len = bis.read(by)) != -1) {
                bos.write(by, 0, len);
                bos.flush();
            }
            if (bos != null)
                bos.close();
            if (bis != null)
                bis.close();
            if (os != null)
                os.close();
            if (is != null)
                is.close();
        }
    }

}
