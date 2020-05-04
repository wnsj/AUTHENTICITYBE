package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingDynamicBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    public Page<BuildingDynamicBean> getDynamicByPage(BuildingDynamicBean buildingDynamicBean);

    public void patchDyById(BuildingDynamicBean buildingDynamicBean);

    public void addDynamic(BuildingDynamicBean buildingDynamicBean);
}
