package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.CounselorBean;

import com.jiubo.buildstore.dao.CounselorDao;
import com.jiubo.buildstore.service.CounselorService;
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
public class CounselorServiceImpl extends ServiceImpl<CounselorDao, CounselorBean> implements CounselorService {

    @Autowired
    private CounselorDao counselorDao;
    @Override
    public List<CounselorBean> getAllCouselor() {
        return counselorDao.getAllCouselor();
    }
}
