package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.LocationTypeBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface LocationTypeService extends IService<LocationTypeBean> {
    public List<LocationTypeBean> getAllLocation ();
}
