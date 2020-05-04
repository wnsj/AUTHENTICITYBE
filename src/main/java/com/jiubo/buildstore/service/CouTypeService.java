package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.CouTypeBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-05-03
 */
public interface CouTypeService extends IService<CouTypeBean> {

    public List<CouTypeBean> getAllCouType();
}
