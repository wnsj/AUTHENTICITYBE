package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.PropertyInfoBean;

import com.jiubo.buildstore.dao.PropertyInfoDao;
import com.jiubo.buildstore.service.PropertyInfoService;
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
 * @since 2020-09-17
 */
@Service
public class PropertyInfoServiceImpl extends ServiceImpl<PropertyInfoDao, PropertyInfoBean> implements PropertyInfoService {

    @Autowired
    private PropertyInfoDao propertyInfoDao;

    @Override
    public List<PropertyInfoBean> getAllPInfo() {
        QueryWrapper<PropertyInfoBean> qwP = new QueryWrapper<PropertyInfoBean>();
        qwP.select("*");
        List<PropertyInfoBean> pictureList = propertyInfoDao.selectList(qwP);
        return pictureList;
    }
}
