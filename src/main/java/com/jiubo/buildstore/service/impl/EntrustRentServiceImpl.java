package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.EntrustRentBean;
import com.jiubo.buildstore.dao.EntrustRentDao;
import com.jiubo.buildstore.service.EntrustRentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

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

}
