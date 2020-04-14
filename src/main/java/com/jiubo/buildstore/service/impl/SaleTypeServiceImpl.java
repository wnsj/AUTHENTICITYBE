package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.SaleTypeBean;

import com.jiubo.buildstore.dao.SaleTypeDao;
import com.jiubo.buildstore.service.SaleTypeService;
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
public class SaleTypeServiceImpl extends ServiceImpl<SaleTypeDao, SaleTypeBean> implements SaleTypeService {


    @Autowired
    private SaleTypeDao saleTypeDao;
    @Override
    public List<SaleTypeBean> getAllSaleType() {
        return saleTypeDao.getAllSaleType();
    }
}
