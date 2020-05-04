package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.RecruitLabelBean;

import com.jiubo.buildstore.bean.RecruitLabelListBean;
import com.jiubo.buildstore.dao.RecruitLabelDao;
import com.jiubo.buildstore.service.RecruitLabelService;
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
public class RecruitLabelServiceImpl extends ServiceImpl<RecruitLabelDao, RecruitLabelBean> implements RecruitLabelService {

    @Autowired
    private RecruitLabelDao recruitLabelDao;



    @Override
    public List<RecruitLabelBean> getLabelByType(RecruitLabelBean recruitLabelBean) {
        return recruitLabelDao.getLabelByType(recruitLabelBean);
    }
}
