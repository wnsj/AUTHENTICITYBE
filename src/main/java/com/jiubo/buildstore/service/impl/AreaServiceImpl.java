package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.AreaBean;

import com.jiubo.buildstore.dao.AreaDao;
import com.jiubo.buildstore.service.AreaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class AreaServiceImpl extends ServiceImpl<AreaDao, AreaBean> implements AreaService {

    @Autowired
    private AreaDao areaDao;
    @Override
    public List<AreaBean> getAllArea(Integer type) {
    	QueryWrapper<AreaBean> queryWrapper = new QueryWrapper<AreaBean>();
    	queryWrapper.select("*");
    	queryWrapper.eq("TYPE", type);
    	
        return areaDao.selectList(queryWrapper);
    }
}
