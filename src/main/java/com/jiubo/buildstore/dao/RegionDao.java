package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.RegionBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-05-01
 */
public interface RegionDao extends BaseMapper<RegionBean> {
    public List<RegionBean> getAllRegion();
}
