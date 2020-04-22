package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingAnalysisBean;

import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.SaleTypeBean;
import com.jiubo.buildstore.dao.BuildingAnalysisDao;
import com.jiubo.buildstore.dao.BuildingDao;
import com.jiubo.buildstore.dao.SaleTypeDao;
import com.jiubo.buildstore.service.BuildingAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private SaleTypeDao saleTypeDao;
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
        page.setCurrent(StringUtils.isBlank(buildingAnalysisBean.getCurrent()) ? 1L : Long.parseLong(buildingAnalysisBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingAnalysisBean.getPageSize()) ? 10L : Long.parseLong(buildingAnalysisBean.getPageSize()));
        List<BuildingBean> allBuild = buildingDao.getAllBuild();
        List<BuildingAnalysisBean> buildAnalysisList = buildingAnalysisDao.getAllAnalysisByBid(page, buildingAnalysisBean);

        // 翻译出售状态
        List<SaleTypeBean> allSaleType = saleTypeDao.getAllSaleType();
        if (null != buildAnalysisList && buildAnalysisList.size() > 0) {

            for (BuildingAnalysisBean bean : buildAnalysisList) {

                if (null != allBuild && allBuild.size() > 0) {
                    Map<Integer, List<BuildingBean>> buildMap = allBuild.stream().collect(Collectors.groupingBy(BuildingBean::getBId));
                    bean.setHtName(buildMap.get(bean.getBuildId()).get(0).getHtName());
                }

                if (null != allSaleType) {
                    Map<Integer, List<SaleTypeBean>> saleMap = allSaleType.stream().collect(Collectors.groupingBy(SaleTypeBean::getStId));

                    bean.setSaleLabel(saleMap.get(bean.getIsSale()).get(0).getStName());
                }

                if (bean.getDrection() == 1) {
                    bean.setDrectionLabel("东");
                } else if (bean.getDrection() == 2) {
                    bean.setDrectionLabel("南");
                } else if (bean.getDrection() == 3) {
                    bean.setDrectionLabel("西");
                } else if (bean.getDrection() == 4) {
                    bean.setDrectionLabel("北");
                }
            }
        }
        return page.setRecords(buildAnalysisList);
    }
}

