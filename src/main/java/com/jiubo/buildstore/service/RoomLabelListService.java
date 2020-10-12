package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.RoomLabelListBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-10-12
 */
public interface RoomLabelListService extends IService<RoomLabelListBean> {

    List<RoomLabelListBean> getAllRlb();
}
