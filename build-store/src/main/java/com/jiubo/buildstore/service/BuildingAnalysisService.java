package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.BuildingAnalysisBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-04-10
 */
public interface BuildingAnalysisService extends IService<BuildingAnalysisBean> {

    public List<BuildingAnalysisBean> getBidByBhtIdList(BuildingAnalysisBean buildingAnalysisBean);

    public List<BuildingAnalysisBean> getBidByBIdList(BuildingAnalysisBean buildingAnalysisBean);
}
