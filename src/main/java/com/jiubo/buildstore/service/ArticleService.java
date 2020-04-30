package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.ArticleBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
public interface ArticleService extends IService<ArticleBean> {
    public List<ArticleBean> getArticleByType(ArticleBean articleBean);

    public Page<ArticleBean> getArticleByPage(ArticleBean articleBean);

    //上传文件
    List<Map<String, Object>> uploadFile(MultipartFile[] file) throws Exception;

    public void insertArticle(ArticleBean articleBean);

    public void updateArticle(ArticleBean articleBean);
}
