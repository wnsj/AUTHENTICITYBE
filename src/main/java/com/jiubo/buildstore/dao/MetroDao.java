package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.MetroBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-06-03
 */
public interface MetroDao extends BaseMapper<MetroBean> {
    public List<MetroBean> getAllMetro();
}
