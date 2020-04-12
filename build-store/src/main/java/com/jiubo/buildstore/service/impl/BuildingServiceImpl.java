package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingAnalysisBean;
import com.jiubo.buildstore.bean.BuildingBean;

import com.jiubo.buildstore.bean.BuildingHorseTypeBean;
import com.jiubo.buildstore.dao.BuildingDao;
import com.jiubo.buildstore.service.BuildingAnalysisService;
import com.jiubo.buildstore.service.BuildingHorseTypeService;
import com.jiubo.buildstore.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingDao, BuildingBean> implements BuildingService {

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private BuildingAnalysisService buildingAnalysisService;

    @Autowired
    private BuildingHorseTypeService buildingHorseTypeService;
    @Override

    public Page<BuildingBean> getAllBulidBypage(BuildingBean buildingBean) {
        Page<BuildingBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingBean.getPageNum()) ? 1L : Long.parseLong(buildingBean.getPageNum()));
        page.setSize(StringUtils.isBlank(buildingBean.getPageSize()) ? 10L : Long.parseLong(buildingBean.getPageSize()));
        // 获取户型id集合
        List<Integer> bhtIdList = buildingBean.getBhtIdList();
        if (null != bhtIdList && bhtIdList.size()>0) {
            BuildingAnalysisBean buildingAnalysisBean = new BuildingAnalysisBean();
            buildingAnalysisBean.setBhtIdList(bhtIdList);
            // 获取户型分析表中楼盘ID
            List<BuildingAnalysisBean> bidByBhtIdList = buildingAnalysisService.getBidByBhtIdList(buildingAnalysisBean);
            List<Integer> bIdList = bidByBhtIdList.stream().map(BuildingAnalysisBean::getBId).collect(Collectors.toList());
            buildingBean.setBIdList(bIdList);
        }


        // 翻译楼盘户型
            // 获取户型分析数据
        List<BuildingHorseTypeBean> allHorseType = buildingHorseTypeService.getAllHorseType();

        // 户型map 通过户型分析中的户型id和楼盘id到户型表中翻译户型
        Map<Integer, List<BuildingHorseTypeBean>> integerListMap = allHorseType.stream().collect(Collectors.groupingBy(BuildingHorseTypeBean::getBhtId));

        List<BuildingBean> allBulidBypage = buildingDao.getAllBulidBypage(page, buildingBean);
        if (null != allBulidBypage && allBulidBypage.size() > 0) {
            // 获取楼盘id
            List<Integer> list = allBulidBypage.stream().map(BuildingBean::getBId).collect(Collectors.toList());
            // 根据楼盘id抓取户型分析数据
            BuildingAnalysisBean buildingAnalysisBean = new BuildingAnalysisBean();
            buildingAnalysisBean.setBIdList(list);
            List<BuildingAnalysisBean> bidByBIdList = buildingAnalysisService.getBidByBIdList(buildingAnalysisBean);
            Map<Integer, List<BuildingAnalysisBean>> listMap = bidByBIdList.stream().collect(Collectors.groupingBy(BuildingAnalysisBean::getBId));
            for (BuildingBean bean : allBulidBypage) {
                List<String> bhtNameList = new ArrayList<>();
                List<BuildingAnalysisBean> buildingAnalysisBeans = listMap.get(bean.getBId());
                if (null != buildingAnalysisBeans && buildingAnalysisBeans.size() > 0) {
                    for (BuildingAnalysisBean bean1 : buildingAnalysisBeans) {
                        List<BuildingHorseTypeBean> buildingHorseTypeBeans = integerListMap.get(bean1.getBhtId());
                        if (null != buildingHorseTypeBeans && buildingHorseTypeBeans.size() > 0) {
                            bhtNameList.add(buildingHorseTypeBeans.get(0).getBhtName());
                        }
                    }
                    // 户型名
                    bean.setCaName(StringUtils.join(bhtNameList,"、"));
                }
            }
        }
        return page.setRecords(allBulidBypage);
    }
}