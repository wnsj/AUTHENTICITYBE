package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.bean.RoomReturn;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

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

	public Map<String, Object> getRoomDetails(Integer roomId);

}
