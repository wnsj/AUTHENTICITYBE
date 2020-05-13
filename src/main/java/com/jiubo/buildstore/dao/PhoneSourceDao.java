package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.PhoneSourceBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-05-13
 */
public interface PhoneSourceDao extends BaseMapper<PhoneSourceBean> {

    public List<PhoneSourceBean> getAllPhoneSource();
}
