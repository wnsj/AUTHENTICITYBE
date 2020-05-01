package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.RegionBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-05-01
 */
public interface RegionService extends IService<RegionBean> {
    public List<RegionBean> getAllRegion();
}
