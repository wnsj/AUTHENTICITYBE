package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.BuildingAnalysisBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface BuildingAnalysisDao extends BaseMapper<BuildingAnalysisBean> {
    public List<BuildingAnalysisBean> getBidByBhtIdList(@Param("buildingAnalysisBean") BuildingAnalysisBean buildingAnalysisBean);
    public List<BuildingAnalysisBean> getBidByBIdList(@Param("buildingAnalysisBean") BuildingAnalysisBean buildingAnalysisBean);
}
