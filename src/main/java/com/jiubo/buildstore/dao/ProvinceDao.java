package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.ProvinceBean;
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
public interface ProvinceDao extends BaseMapper<ProvinceBean> {

    public List<ProvinceBean> getAllProvince();
}
