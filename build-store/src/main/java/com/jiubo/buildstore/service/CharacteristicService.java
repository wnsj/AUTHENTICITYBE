package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.CharacteristicBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface CharacteristicService extends IService<CharacteristicBean> {
    public List<CharacteristicBean> getAllChara();
}
