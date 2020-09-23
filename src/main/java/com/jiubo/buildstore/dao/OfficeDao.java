package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.OfficeBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author swd
 * @since 2020-09-13
 */
public interface OfficeDao extends BaseMapper<OfficeBean> {
    List<OfficeBean> getOffByRoomIdList(List<Integer> roomIdList);
    void patchOffFlagByRoomId(OfficeBean officeBean);
}
