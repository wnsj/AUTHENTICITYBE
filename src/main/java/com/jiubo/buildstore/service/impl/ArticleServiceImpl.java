package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.ArticleBean;
import com.jiubo.buildstore.dao.ArticleDao;
import com.jiubo.buildstore.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
