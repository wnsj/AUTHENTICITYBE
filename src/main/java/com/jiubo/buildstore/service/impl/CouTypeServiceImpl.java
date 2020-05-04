package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.CouTypeBean;
import com.jiubo.buildstore.dao.CouTypeDao;
import com.jiubo.buildstore.service.CouTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dx
 * @since 2020-05-03
 */
@Service
public class CouTypeServiceImpl extends ServiceImpl<CouTypeDao, CouTypeBean> implements CouTypeService {


    @Autowired
    private CouTypeDao couTypeDao;
    @Override
    public List<CouTypeBean> getAllCouType() {
        return couTypeDao.getAllCouType();
    }
}
