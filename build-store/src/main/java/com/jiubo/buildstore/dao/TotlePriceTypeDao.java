package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.TotlePriceTypeBean;
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
public interface TotlePriceTypeDao extends BaseMapper<TotlePriceTypeBean> {
    public List<TotlePriceTypeBean> getAllTotalPrice();
}
