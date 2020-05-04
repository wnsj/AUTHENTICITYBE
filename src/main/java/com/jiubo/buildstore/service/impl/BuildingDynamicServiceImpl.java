package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.BuildingDynamicBean;

import com.jiubo.buildstore.dao.BuildingDao;
import com.jiubo.buildstore.dao.BuildingDynamicDao;
import com.jiubo.buildstore.service.BuildingDynamicService;
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
public class BuildingDynamicServiceImpl extends ServiceImpl<BuildingDynamicDao, BuildingDynamicBean> implements BuildingDynamicService {

    @Autowired
    private BuildingDynamicDao buildingDynamicDao;
    @Autowired
    private BuildingDao buildingDao;
    @Override
    public Page<BuildingDynamicBean> getDynamicByBid(BuildingDynamicBean buildingDynamicBean) {

        Page<BuildingDynamicBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingDynamicBean.getCurrent()) ? 1L : Long.parseLong(buildingDynamicBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingDynamicBean.getPageSize()) ? 3L : Long.parseLong(buildingDynamicBean.getPageSize()));
        return page.setRecords(buildingDynamicDao.getDynamicByBid(page,buildingDynamicBean));
    }

    @Override
    public Page<BuildingDynamicBean> getDynamicByPage(BuildingDynamicBean buildingDynamicBean) {
        Page<BuildingDynamicBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingDynamicBean.getCurrent()) ? 1L : Long.parseLong(buildingDynamicBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingDynamicBean.getPageSize()) ? 10L : Long.parseLong(buildingDynamicBean.getPageSize()));
        List<BuildingDynamicBean> dynamicBeans = buildingDynamicDao.getDynamicByPage(page, buildingDynamicBean);
        if (null != dynamicBeans && dynamicBeans.size()>0) {
            List<BuildingBean> buildList = buildingDao.getAllBuild();
            Map<Integer, List<BuildingBean>> collect = null;
            if (null != buildList && buildList.size()>0){
                collect = buildList.stream().collect(Collectors.groupingBy(BuildingBean::getBuildId));
            }
            for (BuildingDynamicBean dynamicBean : dynamicBeans) {
                dynamicBean.setHtName(collect.get(dynamicBean.getBuildId()).get(0).getHtName());
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
