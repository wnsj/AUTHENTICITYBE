package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.CommercialActivitieBean;
import com.jiubo.buildstore.dao.CommercialActivitieDao;
import com.jiubo.buildstore.service.CommercialActivitieService;
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
public class CommercialActivitieServiceImpl extends ServiceImpl<CommercialActivitieDao, CommercialActivitieBean> implements CommercialActivitieService {

    @Autowired
    private CommercialActivitieDao commercialActivitieDao;

    @Override
    public List<CommercialActivitieBean> getAllComAc() {
        QueryWrapper<CommercialActivitieBean> qw = new QueryWrapper<CommercialActivitieBean>();
        qw.select("*");
        return commercialActivitieDao.selectList(qw);
    }
}
