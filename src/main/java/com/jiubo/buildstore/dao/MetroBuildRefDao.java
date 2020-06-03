package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.MetroBuildRefBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-06-03
 */
public interface MetroBuildRefDao extends BaseMapper<MetroBuildRefBean> {

    public void insertMBRefBatch(List<MetroBuildRefBean> metroBuildRefBeanList);

    public List<MetroBuildRefBean> getAllMBRefByBIds(@Param("metroBuildRefBean") MetroBuildRefBean metroBuildRefBean);

    public void deleteMBRefByBid(@Param("buildId") Integer buildId);
}
