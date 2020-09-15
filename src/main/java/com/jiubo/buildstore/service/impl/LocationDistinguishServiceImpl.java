package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.LocationDistinguishBean;

import com.jiubo.buildstore.dao.LocationDistinguishDao;
import com.jiubo.buildstore.service.LocationDistinguishService;
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
public class LocationDistinguishServiceImpl extends ServiceImpl<LocationDistinguishDao, LocationDistinguishBean> implements LocationDistinguishService {

    @Autowired
    private LocationDistinguishDao locationDistinguishDao;
    @Override
    public List<LocationDistinguishBean> getAllDistinguish(Integer proId) {
        return locationDistinguishDao.getAllDistinguish(proId);
    }
}
