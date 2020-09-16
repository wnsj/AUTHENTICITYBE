package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.EntrustRentBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

	Page<EntrustRentBean> getEnByPage(EntrustRentBean entrustRentBean);

	public Integer updateEntrustRent(Integer enId, Integer isConcat, String remark);

}
