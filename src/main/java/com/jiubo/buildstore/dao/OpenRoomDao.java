package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.AloneRoomBean;
import com.jiubo.buildstore.bean.OpenRoomBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 开放工位表 Mapper 接口
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
public interface OpenRoomDao extends BaseMapper<OpenRoomBean> {

    List<OpenRoomBean> getOpenRoomByRoomId(@Param("roomId") Integer roomId);
}
