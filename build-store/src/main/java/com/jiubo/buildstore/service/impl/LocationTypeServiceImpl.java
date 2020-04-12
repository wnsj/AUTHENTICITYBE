package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.LocationTypeBean;

import com.jiubo.buildstore.dao.LocationTypeDao;
import com.jiubo.buildstore.service.LocationTypeService;
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
public class LocationTypeServiceImpl extends ServiceImpl<LocationTypeDao, LocationTypeBean> implements LocationTypeService {

    @Autowired
    private LocationTypeDao locationTypeDao;
    @Override
    public List<LocationTypeBean> getAllLocation() {
        return locationTypeDao.getAllLocation();
    }
}
