package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.bean.CommercialActivitieBean;
import com.jiubo.buildstore.bean.StoreTypeBean;
import com.jiubo.buildstore.dao.StoreTypeDao;
import com.jiubo.buildstore.service.StoreTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@Service
public class StoreTypeServiceImpl extends ServiceImpl<StoreTypeDao, StoreTypeBean> implements StoreTypeService {

    @Autowired
    private StoreTypeDao storeTypeDao;
    @Override
    public List<StoreTypeBean> getAllStoreType() {
        QueryWrapper<StoreTypeBean> qw = new QueryWrapper<StoreTypeBean>();
        qw.select("*");
        return storeTypeDao.selectList(qw);
    }
}
