package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.StoreTypeBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
public interface StoreTypeService extends IService<StoreTypeBean> {
    List<StoreTypeBean> getAllStoreType();
}
