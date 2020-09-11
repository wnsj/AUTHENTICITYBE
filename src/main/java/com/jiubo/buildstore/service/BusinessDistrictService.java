package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.BusinessDistrictBean;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
public interface BusinessDistrictService extends IService<BusinessDistrictBean> {

	public List<BusinessDistrictBean> getBusinessDistrict(Integer ldId);

}
