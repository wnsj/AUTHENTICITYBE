package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.BaseServiceBean;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-17
 */
public interface BaseServiceService extends IService<BaseServiceBean> {

	public List<BaseServiceBean> getAllBaseService();

}
