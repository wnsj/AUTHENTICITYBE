package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.EntrustRentBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
public interface EntrustRentService extends IService<EntrustRentBean> {
	
	public void insertEntrustRent(EntrustRentBean entrustRentBean);

}
