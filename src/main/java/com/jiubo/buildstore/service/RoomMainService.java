package com.jiubo.buildstore.service;

import com.github.pagehelper.PageInfo;
import com.jiubo.buildstore.bean.RMChildSharedBean;
import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.bean.RoomReceive;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 房源主表 服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
public interface RoomMainService extends IService<RoomMainBean> {

	public PageInfo<RoomMainBean> getRoomByConditions(RoomReceive receive);

	public Map<String, Object> getRoomDetails(Integer roomMainId);

	RMChildSharedBean getSharedById(Integer id);


	public Map<String, Object> getStoneDetail(Integer roomMainId);
}