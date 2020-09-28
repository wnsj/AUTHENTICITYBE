package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BuildingDynamicBean;
import com.jiubo.buildstore.bean.BuildingTypeBean;

import com.jiubo.buildstore.dao.BuildingTypeDao;
import com.jiubo.buildstore.service.BuildingTypeService;
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
public class BuildingTypeServiceImpl extends ServiceImpl<BuildingTypeDao, BuildingTypeBean> implements BuildingTypeService {

    @Autowired
    private BuildingTypeDao buildingTypeDao;
    @Override
    public List<BuildingTypeBean> getAllBuildingType(Integer type) {
    	QueryWrapper<BuildingTypeBean> queryWrapper = new QueryWrapper<BuildingTypeBean>();
		queryWrapper.select("*");
		if(type != null && type != 0) {
			queryWrapper.eq("TYPE", type);
		}
        return buildingTypeDao.selectList(queryWrapper);
    }
}
