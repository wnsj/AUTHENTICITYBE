package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.RMChildSharedBean;
import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.bean.RoomReceive;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 房源主表 Mapper 接口
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
public interface RoomMainDao extends BaseMapper<RoomMainBean> {

	List<RoomMainBean> getAllRoomBypage(@Param("receive") RoomReceive receive);

	List<RoomMainBean> getAllRoomByPageBe(@Param("receive") RoomReceive receive);

	List<RoomMainBean> getRoomByBuildIdList(List<Integer> buildIdList);

	void patchRoomFlagById(RoomMainBean roomMainBean);
}
