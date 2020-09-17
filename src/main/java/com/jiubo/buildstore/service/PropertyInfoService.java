package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.PropertyInfoBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-17
 */
public interface PropertyInfoService extends IService<PropertyInfoBean> {

    List<PropertyInfoBean> getAllPInfo();
}
