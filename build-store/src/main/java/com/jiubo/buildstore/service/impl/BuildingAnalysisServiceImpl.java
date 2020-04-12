package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BuildingAnalysisBean;

import com.jiubo.buildstore.dao.BuildingAnalysisDao;
import com.jiubo.buildstore.service.BuildingAnalysisService;
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
public class BuildingAnalysisServiceImpl extends ServiceImpl<BuildingAnalysisDao, BuildingAnalysisBean> implements BuildingAnalysisService {

    @Autowired
    private BuildingAnalysisDao buildingAnalysisDao;
    @Override
    public List<BuildingAnalysisBean> getBidByBhtIdList(BuildingAnalysisBean buildingAnalysisBean) {
        return buildingAnalysisDao.getBidByBhtIdList(buildingAnalysisBean);
    }

    @Override
    public List<BuildingAnalysisBean> getBidByBIdList(BuildingAnalysisBean buildingAnalysisBean) {
        return buildingAnalysisDao.getBidByBIdList(buildingAnalysisBean);
    }
}

