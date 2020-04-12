package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.TotlePriceTypeBean;

import com.jiubo.buildstore.dao.TotlePriceTypeDao;
import com.jiubo.buildstore.service.TotlePriceTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dx
 * @since 2020-04-10
 */
@Service
public class TotlePriceTypeServiceImpl extends ServiceImpl<TotlePriceTypeDao, TotlePriceTypeBean> implements TotlePriceTypeService {

    @Autowired
    private TotlePriceTypeDao totlePriceTypeDao;


    @Override
    public List<TotlePriceTypeBean> getAllTotalPrice() {
        return totlePriceTypeDao.getAllTotalPrice();
    }
}
