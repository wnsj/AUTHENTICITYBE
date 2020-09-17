package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.MessageTypeBean;

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
public interface MessageTypeService extends IService<MessageTypeBean> {

	public List<MessageTypeBean> getAllMessageType();

}
