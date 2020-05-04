package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.AreaBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface AreaDao extends BaseMapper<AreaBean> {
    public List<AreaBean> getAllArea();

    public List<AreaBean> getAreaByIdList(@Param("areaBean") AreaBean areaBean);
}
