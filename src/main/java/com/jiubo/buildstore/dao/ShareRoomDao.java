package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.ShareRoomBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 共享房源 Mapper 接口
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
public interface ShareRoomDao extends BaseMapper<ShareRoomBean> {
    ShareRoomBean getShareRoomByRoomId(@Param("roomId") Integer roomId);
}
