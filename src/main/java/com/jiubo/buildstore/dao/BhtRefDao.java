package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.BhtRefBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-04-30
 */
public interface BhtRefDao extends BaseMapper<BhtRefBean> {
    public void insertBhtRefBatch(List<BhtRefBean> bhtRefBeanList);

    public List<BhtRefBean>getAllBhtRefByBIds(@Param("bhtRefBean") BhtRefBean bhtRefBean);

    public void deleteBhtRefByBid(@Param("buildId") Integer buildId);
}
