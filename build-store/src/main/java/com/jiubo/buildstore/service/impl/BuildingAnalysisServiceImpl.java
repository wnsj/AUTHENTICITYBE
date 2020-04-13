package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingAnalysisBean;

import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.dao.BuildingAnalysisDao;
import com.jiubo.buildstore.service.BuildingAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public Page<BuildingAnalysisBean> getAllAnalysisByBid(BuildingAnalysisBean buildingAnalysisBean) {
        Page<BuildingAnalysisBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingAnalysisBean.getPageNum()) ? 1L : Long.parseLong(buildingAnalysisBean.getPageNum()));
        page.setSize(StringUtils.isBlank(buildingAnalysisBean.getPageSize()) ? 10L : Long.parseLong(buildingAnalysisBean.getPageSize()));
        return page.setRecords(buildingAnalysisDao.getAllAnalysisByBid(page,buildingAnalysisBean));
    }
}

