package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.MetroBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-06-03
 */
public interface MetroService extends IService<MetroBean> {
    public List<MetroBean> getAllMetro();
}
