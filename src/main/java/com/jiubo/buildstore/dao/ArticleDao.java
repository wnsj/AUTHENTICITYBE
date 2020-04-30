package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.ArticleBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
public interface ArticleDao extends BaseMapper<ArticleBean> {

    public List<ArticleBean> getArticleByType(@Param("articleBean") ArticleBean articleBean);
    public List<ArticleBean> getArticleByPage(Page page, @Param("articleBean") ArticleBean articleBean);
}
