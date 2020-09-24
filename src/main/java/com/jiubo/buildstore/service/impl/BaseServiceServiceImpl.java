package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BaseServiceBean;
import com.jiubo.buildstore.dao.BaseServiceDao;
import com.jiubo.buildstore.service.BaseServiceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-17
 */
@Service
public class BaseServiceServiceImpl extends ServiceImpl<BaseServiceDao, BaseServiceBean> implements BaseServiceService {
	
	@Autowired
	private BaseServiceDao baseServiceDao;

	@Override
	public List<BaseServiceBean> getAllBaseService() {
		QueryWrapper<BaseServiceBean> qw = new QueryWrapper<BaseServiceBean>();
		qw.select("*");
		return baseServiceDao.selectList(qw);
	}

}
