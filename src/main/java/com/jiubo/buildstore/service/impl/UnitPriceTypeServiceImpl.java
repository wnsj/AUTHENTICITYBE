package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.UnitPriceTypeBean;

import com.jiubo.buildstore.dao.UnitPriceTypeDao;
import com.jiubo.buildstore.service.UnitPriceTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Service
public class UnitPriceTypeServiceImpl extends ServiceImpl<UnitPriceTypeDao, UnitPriceTypeBean> implements UnitPriceTypeService {


    @Autowired
    private UnitPriceTypeDao unitPriceTypeDao;
    @Override
    public List<UnitPriceTypeBean> getAllUnitPrice() {
        return unitPriceTypeDao.getAllUnitPrice();
    }
}
