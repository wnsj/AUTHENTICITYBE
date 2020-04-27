package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.RecruitTypeBean;
import com.jiubo.buildstore.dao.RecruitTypeDao;
import com.jiubo.buildstore.service.RecruitTypeService;
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
public class RecruitTypeServiceImpl extends ServiceImpl<RecruitTypeDao, RecruitTypeBean> implements RecruitTypeService {

    @Autowired
    private RecruitTypeDao recruitTypeDao;
    @Override
    public List<RecruitTypeBean> getAllRecruitType() {
        return recruitTypeDao.getAllRecruitType();
    }
}
