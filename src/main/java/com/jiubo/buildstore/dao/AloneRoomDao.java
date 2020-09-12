package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.AloneRoomBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 独立办公室 Mapper 接口
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
public interface AloneRoomDao extends BaseMapper<AloneRoomBean> {
    List<AloneRoomBean> getAloneRoomByRoomId(@Param("roomId") Integer roomId);
}
