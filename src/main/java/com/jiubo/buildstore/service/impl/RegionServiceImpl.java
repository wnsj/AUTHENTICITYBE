package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.RegionBean;
import com.jiubo.buildstore.dao.RegionDao;
import com.jiubo.buildstore.service.RegionService;
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
 * @since 2020-05-01
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionDao, RegionBean> implements RegionService {

    @Autowired
    private RegionDao regionDao;
    @Override
    public List<RegionBean> getAllRegion() {
        return regionDao.getAllRegion();
    }
}
