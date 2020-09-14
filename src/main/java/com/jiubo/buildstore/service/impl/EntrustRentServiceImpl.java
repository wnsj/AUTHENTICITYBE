package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.EntrustRentBean;
import com.jiubo.buildstore.dao.EntrustRentDao;
import com.jiubo.buildstore.service.EntrustRentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
@Service
public class EntrustRentServiceImpl extends ServiceImpl<EntrustRentDao, EntrustRentBean> implements EntrustRentService {
	
	@Autowired
    private EntrustRentDao entrustRentDao;
	
	@Override
	public void insertEntrustRent(EntrustRentBean entrustRentBean) {
		entrustRentBean.setCreateTime(new Date());
		entrustRentBean.setIsContact(3);
		entrustRentDao.insert(entrustRentBean);
	}

	@Override
	public Page<EntrustRentBean> getEnByPage(EntrustRentBean entrustRentBean) {
		Page<EntrustRentBean> page = new Page<>();
		page.setCurrent(StringUtils.isBlank(entrustRentBean.getCurrent()) ? 1L : Long.parseLong(entrustRentBean.getCurrent()));
		page.setSize(StringUtils.isBlank(entrustRentBean.getPageSize()) ? 10L : Long.parseLong(entrustRentBean.getPageSize()));
		List<EntrustRentBean> enByPageList = entrustRentDao.getEnByPage(page, entrustRentBean);
		return page.setRecords(enByPageList);
	}

}
