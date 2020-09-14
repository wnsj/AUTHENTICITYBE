package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.TotlePriceTypeBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface TotlePriceTypeService extends IService<TotlePriceTypeBean> {
    public List<TotlePriceTypeBean> getAllTotalPrice(Integer type);
}
