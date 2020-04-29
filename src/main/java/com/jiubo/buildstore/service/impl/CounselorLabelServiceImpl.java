package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.CounselorLabelBean;

import com.jiubo.buildstore.dao.CounselorLabelDao;
import com.jiubo.buildstore.service.CounselorLabelService;
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
 * @since 2020-04-10
 */
@Service
public class CounselorLabelServiceImpl extends ServiceImpl<CounselorLabelDao, CounselorLabelBean> implements CounselorLabelService {

    @Autowired
    private CounselorLabelDao counselorLabelDao;

    @Override
    public List<CounselorLabelBean> getAllCouLabel() {
        return counselorLabelDao.getAllCouLabel();
    }
}
