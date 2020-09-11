package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.TotlePriceTypeBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface TotlePriceTypeDao extends BaseMapper<TotlePriceTypeBean> {
    public List<TotlePriceTypeBean> getAllTotalPrice();
    
    public List<TotlePriceTypeBean> getTotalPriceByIdList(@Param("totlePriceTypeBean") TotlePriceTypeBean totlePriceTypeBean);
}
