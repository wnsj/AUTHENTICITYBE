package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BuildingHorseTypeBean;

import com.jiubo.buildstore.dao.BuildingAnalysisDao;
import com.jiubo.buildstore.dao.BuildingHorseTypeDao;
import com.jiubo.buildstore.service.BuildingHorseTypeService;
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
 * @since 2020-04-10
 */
@Service
public class BuildingHorseTypeServiceImpl extends ServiceImpl<BuildingHorseTypeDao, BuildingHorseTypeBean> implements BuildingHorseTypeService {

    @Autowired
    private BuildingHorseTypeDao buildingHorseTypeDao;
    @Override
    public List<BuildingHorseTypeBean> getAllHorseType() {
        return buildingHorseTypeDao.getAllHorseType();
    }
}
