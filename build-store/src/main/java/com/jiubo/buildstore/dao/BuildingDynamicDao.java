package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingDynamicBean;
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
public interface BuildingDynamicDao extends BaseMapper<BuildingDynamicBean> {
    public List<BuildingDynamicBean> getDynamicByBid(Page page, @Param("buildingDynamicBean") BuildingDynamicBean buildingDynamicBean);
}
