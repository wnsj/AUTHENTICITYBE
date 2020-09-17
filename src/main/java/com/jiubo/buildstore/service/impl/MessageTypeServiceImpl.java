package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.MessageTypeBean;
import com.jiubo.buildstore.dao.MessageTypeDao;
import com.jiubo.buildstore.service.MessageTypeService;
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
public class MessageTypeServiceImpl extends ServiceImpl<MessageTypeDao, MessageTypeBean> implements MessageTypeService {
	
	@Autowired
	private MessageTypeDao messageTypeDao;

	@Override
	public List<MessageTypeBean> getAllMessageType() {
		QueryWrapper<MessageTypeBean> qw = new QueryWrapper<MessageTypeBean>();
		qw.select("*");
		return messageTypeDao.selectList(qw);
	}

}
