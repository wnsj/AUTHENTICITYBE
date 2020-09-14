package com.jiubo.buildstore.service;

import com.github.pagehelper.PageInfo;
import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.RoomReceive;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
public interface RoomService extends IService<RoomBean> {

	public PageInfo<RoomBean> getRoomByConditions(RoomReceive receive);

}
