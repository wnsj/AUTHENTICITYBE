package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.EntrustRentBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
public interface EntrustRentDao extends BaseMapper<EntrustRentBean> {

    List<EntrustRentBean> getEnByPage(Page page, @Param("entrustRentBean") EntrustRentBean entrustRentBean);
}
