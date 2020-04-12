package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-04-10
 */
public interface BuildingService extends IService<BuildingBean> {
    public Page<BuildingBean> getAllBulidBypage(BuildingBean buildingBean);
}
