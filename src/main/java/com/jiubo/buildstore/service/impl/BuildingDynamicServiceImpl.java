package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.BuildingDynamicBean;

import com.jiubo.buildstore.dao.BuildingDao;
import com.jiubo.buildstore.dao.BuildingDynamicDao;
import com.jiubo.buildstore.service.BuildingDynamicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
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
public class BuildingDynamicServiceImpl extends ServiceImpl<BuildingDynamicDao, BuildingDynamicBean> implements BuildingDynamicService {

    @Autowired
    private BuildingDynamicDao buildingDynamicDao;
    @Autowired
    private BuildingDao buildingDao;
    @Override
    public List<BuildingDynamicBean> getDynamicByBid(BuildingDynamicBean buildingDynamicBean) {

//        Page<BuildingDynamicBean> page = new Page<>();
//        page.setCurrent(StringUtils.isBlank(buildingDynamicBean.getCurrent()) ? 1L : Long.parseLong(buildingDynamicBean.getCurrent()));
//        page.setSize(StringUtils.isBlank(buildingDynamicBean.getPageSize()) ? 3L : Long.parseLong(buildingDynamicBean.getPageSize()));
        List<BuildingDynamicBean> dynamicByBidList = buildingDynamicDao.getDynamicByBid(buildingDynamicBean);
        if (null != dynamicByBidList && dynamicByBidList.size()>0) {
            for (BuildingDynamicBean dynamicBean : dynamicByBidList) {
                if (dynamicBean.getCreateDate() != null) {
                    dynamicBean.setCreateTime(DateUtils.formatDate(dynamicBean.getCreateDate(),"yyyy-MM-dd"));
                }
            }
        }
        return dynamicByBidList;
    }

    @Override
    public BuildingDynamicBean getNewestDynamicByBid(BuildingDynamicBean buildingDynamicBean) {
        List<BuildingDynamicBean> dynamicByBidList = buildingDynamicDao.getDynamicByBid(buildingDynamicBean);
        BuildingDynamicBean bean = new BuildingDynamicBean();
        if (!CollectionsUtils.isEmpty(dynamicByBidList)) {
            List<BuildingDynamicBean> dynamicBeans = dynamicByBidList.stream().sorted(Comparator.comparing(BuildingDynamicBean::getCreateDate).reversed()).limit(1).collect(Collectors.toList());
            bean = dynamicBeans.get(0);
            bean.setDyCount(dynamicByBidList.size());
            for (BuildingDynamicBean dynamicBean : dynamicByBidList) {
                if (dynamicBean.getCreateDate() != null) {
                    dynamicBean.setCreateTime(DateUtils.formatDate(dynamicBean.getCreateDate(),"yyyy-MM-dd"));
                }
            }
        } else {
            bean.setDyCount(0);
        }
        return bean;
    }

    @Override
    public Page<BuildingDynamicBean> getDynamicByPage(BuildingDynamicBean buildingDynamicBean) {
        Page<BuildingDynamicBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingDynamicBean.getCurrent()) ? 1L : Long.parseLong(buildingDynamicBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingDynamicBean.getPageSize()) ? 10L : Long.parseLong(buildingDynamicBean.getPageSize()));
        List<BuildingDynamicBean> dynamicBeans = buildingDynamicDao.getDynamicByPage(page, buildingDynamicBean);
        if (null != dynamicBeans && dynamicBeans.size()>0) {
            for (BuildingDynamicBean dynamicBean : dynamicBeans) {
                if(dynamicBean.getBuildId() != null){
                    Date date = dynamicBean.getCreateDate();
                    if (null != date) {
                        dynamicBean.setCreateTime(DateUtils.formatDate(date,"yyyy-MM-dd"));
                    }
                }
            }
        }
        return page.setRecords(dynamicBeans);
    }

    @Override
    public void patchDyById(BuildingDynamicBean buildingDynamicBean) {
        buildingDynamicDao.patchDyById(buildingDynamicBean);
    }

    @Override
    public void addDynamic(BuildingDynamicBean buildingDynamicBean) {

        buildingDynamicDao.addDynamic(buildingDynamicBean);
    }
}
