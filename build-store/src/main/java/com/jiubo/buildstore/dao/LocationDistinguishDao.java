package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.LocationDistinguishBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface LocationDistinguishDao extends BaseMapper<LocationDistinguishBean> {
    public List<LocationDistinguishBean> getAllDistinguish(@Param("locationDistinguishBean") LocationDistinguishBean locationDistinguishBean);
}
