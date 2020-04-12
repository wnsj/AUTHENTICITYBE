package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.BuildingHorseTypeBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-04-10
 */
public interface BuildingHorseTypeDao extends BaseMapper<BuildingHorseTypeBean> {
    public List<BuildingHorseTypeBean> getAllHorseType();
}
