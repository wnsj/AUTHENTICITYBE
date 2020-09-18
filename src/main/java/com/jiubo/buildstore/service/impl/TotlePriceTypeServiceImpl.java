package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.TotlePriceTypeBean;

import com.jiubo.buildstore.dao.TotlePriceTypeDao;
import com.jiubo.buildstore.service.TotlePriceTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class TotlePriceTypeServiceImpl extends ServiceImpl<TotlePriceTypeDao, TotlePriceTypeBean> implements TotlePriceTypeService {

    @Autowired
    private TotlePriceTypeDao totlePriceTypeDao;


    @Override
    public List<TotlePriceTypeBean> getAllTotalPrice(Integer type) {
    	QueryWrapper<TotlePriceTypeBean> queryWrapper = new QueryWrapper<TotlePriceTypeBean>();
    	queryWrapper.select("*");
    	if(type != null) {
    		queryWrapper.eq("TYPE", type);
    	}
        return totlePriceTypeDao.selectList(queryWrapper);
    }
}
