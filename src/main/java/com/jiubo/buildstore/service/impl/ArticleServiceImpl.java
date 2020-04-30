package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.ArticleBean;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.dao.ArticleDao;
import com.jiubo.buildstore.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ArticleBean> getArticleByType(ArticleBean articleBean) {
        return articleDao.getArticleByType(articleBean);
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
                } else {
                    bean.setArticleTypeLabel("企业文化");
                }
            }
        }
        page.setRecords(articleByPage);
        return page;
    }
}
