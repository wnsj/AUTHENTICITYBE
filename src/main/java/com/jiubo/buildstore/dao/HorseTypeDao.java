package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.HorseTypeBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface HorseTypeDao extends BaseMapper<HorseTypeBean> {

    public List<HorseTypeBean> getAllHorseType();
}
