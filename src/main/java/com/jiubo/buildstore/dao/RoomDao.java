package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.bean.RoomReturn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
public interface RoomDao extends BaseMapper<RoomBean> {
		
	public List<RoomBean> getAllRoomBypage(@Param("receive") RoomReceive receive);

}
