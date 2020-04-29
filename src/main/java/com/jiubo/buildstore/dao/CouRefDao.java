package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.CouRefBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiubo.buildstore.bean.CounselorBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-04-28
 */
public interface CouRefDao extends BaseMapper<CouRefBean> {
public List<CouRefBean> getRefByCouIdList(@Param("couRefBean") CouRefBean couRefBean);

    public void insertCouRefBatch(List<CouRefBean> couRefBeanList);

    public void deleteRefByCouId(@Param("couId") Integer couId);
}
