package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.BuildingDynamicBean;

import com.jiubo.buildstore.dao.BuildingDynamicDao;
import com.jiubo.buildstore.service.BuildingDynamicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public Page<BuildingDynamicBean> getDynamicByBid(BuildingDynamicBean buildingDynamicBean) {

        Page<BuildingDynamicBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingDynamicBean.getCurrent()) ? 1L : Long.parseLong(buildingDynamicBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingDynamicBean.getPageSize()) ? 3L : Long.parseLong(buildingDynamicBean.getPageSize()));
        return page.setRecords(buildingDynamicDao.getDynamicByBid(page,buildingDynamicBean));
    }
}
