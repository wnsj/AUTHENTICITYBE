package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.BuildingHorseTypeBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-04-10
 */
public interface BuildingHorseTypeService extends IService<BuildingHorseTypeBean> {
    public List<BuildingHorseTypeBean> getAllHorseType();
}
