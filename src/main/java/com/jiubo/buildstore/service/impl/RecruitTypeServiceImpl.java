package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.RecruitTypeBean;
import com.jiubo.buildstore.dao.RecruitTypeDao;
import com.jiubo.buildstore.service.RecruitTypeService;
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
public class RecruitTypeServiceImpl extends ServiceImpl<RecruitTypeDao, RecruitTypeBean> implements RecruitTypeService {

    @Autowired
    private RecruitTypeDao recruitTypeDao;
    @Override
    public List<RecruitTypeBean> getAllRecruitType() {
        return recruitTypeDao.getAllRecruitType();
    }

    @Override
    public void addRecruitType(RecruitTypeBean recruitTypeBean) throws MessageException {
        if (StringUtils.isBlank(recruitTypeBean.getTypeName())) throw new MessageException("类型名不能为空");
        QueryWrapper<RecruitTypeBean> queryWrapper = new QueryWrapper<RecruitTypeBean>();
        queryWrapper.select("*");
        queryWrapper.eq("TYPE_NAME", recruitTypeBean.getTypeName());
        List<RecruitTypeBean> recruitTypeBeans = recruitTypeDao.selectList(queryWrapper);
        if (recruitTypeBeans.size()>0)  throw new MessageException("类型已经存在");
        recruitTypeDao.insert(recruitTypeBean);
    }
}
