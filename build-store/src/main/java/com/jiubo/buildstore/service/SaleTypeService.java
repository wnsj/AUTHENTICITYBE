package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.SaleTypeBean;
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
public interface SaleTypeService extends IService<SaleTypeBean> {
    public List<SaleTypeBean> getAllSaleType();
}
