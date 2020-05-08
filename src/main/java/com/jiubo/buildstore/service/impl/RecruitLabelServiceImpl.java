package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.RecruitLabelBean;

import com.jiubo.buildstore.bean.RecruitLabelListBean;
import com.jiubo.buildstore.bean.RecruitTypeBean;
import com.jiubo.buildstore.dao.RecruitLabelDao;
import com.jiubo.buildstore.service.RecruitLabelService;
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
public class RecruitLabelServiceImpl extends ServiceImpl<RecruitLabelDao, RecruitLabelBean> implements RecruitLabelService {

    @Autowired
    private RecruitLabelDao recruitLabelDao;



    @Override
    public List<RecruitLabelBean> getLabelByType(RecruitLabelBean recruitLabelBean) {
        return recruitLabelDao.getLabelByType(recruitLabelBean);
    }

    @Override
    public void addRecruitLabel(RecruitLabelBean recruitLabelBean) throws MessageException{
        if (StringUtils.isBlank(recruitLabelBean.getRecruitName())) throw new MessageException("岗位名不能为空");
        if (null == recruitLabelBean.getTypeId() || recruitLabelBean.getTypeId()==0) throw new MessageException("岗位类型不能为空");
        QueryWrapper<RecruitLabelBean> queryWrapper = new QueryWrapper<RecruitLabelBean>();
        queryWrapper.select("*");
        queryWrapper.eq("TYPE_ID", recruitLabelBean.getTypeId());
        queryWrapper.eq("RECRUIT_NAME", recruitLabelBean.getRecruitName());
        List<RecruitLabelBean> recruitTypeBeans = recruitLabelDao.selectList(queryWrapper);
        if (recruitTypeBeans.size()>0)  throw new MessageException("类型中岗位已经存在");
        recruitLabelDao.insert(recruitLabelBean);
    }
}
