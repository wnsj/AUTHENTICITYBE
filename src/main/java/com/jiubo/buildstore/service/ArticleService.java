package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.ArticleBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-04-25
 */
public interface ArticleService extends IService<ArticleBean> {
    public List<ArticleBean> getArticleByType(ArticleBean articleBean);
}
