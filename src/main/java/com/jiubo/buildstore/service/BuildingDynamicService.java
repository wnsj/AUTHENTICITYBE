package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingDynamicBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface BuildingDynamicService extends IService<BuildingDynamicBean> {
    public Page<BuildingDynamicBean> getDynamicByBid(BuildingDynamicBean buildingDynamicBean);
}
