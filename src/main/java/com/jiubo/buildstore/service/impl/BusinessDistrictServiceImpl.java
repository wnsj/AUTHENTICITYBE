package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BusinessDistrictBean;
import com.jiubo.buildstore.dao.BusinessDistrictDao;
import com.jiubo.buildstore.service.BusinessDistrictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
@Service
public class BusinessDistrictServiceImpl extends ServiceImpl<BusinessDistrictDao, BusinessDistrictBean>
		implements BusinessDistrictService {

	@Autowired
	private BusinessDistrictDao businessDistrictDao;

	@Override
	public List<BusinessDistrictBean> getBusinessDistrict(Integer ldId) {
		if (ldId != null) {
			QueryWrapper<BusinessDistrictBean> queryWrapper = new QueryWrapper<BusinessDistrictBean>();
			queryWrapper.select("*");
			queryWrapper.eq("ld_id", ldId);
			List<BusinessDistrictBean> list = businessDistrictDao.selectList(queryWrapper);
			return list;
		}
		return null;
	}

}
