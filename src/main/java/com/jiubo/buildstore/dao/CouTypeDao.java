package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.CouTypeBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-05-03
 */
public interface CouTypeDao extends BaseMapper<CouTypeBean> {

    public List<CouTypeBean> getAllCouType();
}
