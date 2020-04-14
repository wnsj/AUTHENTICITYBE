package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.ProvinceBean;

import com.jiubo.buildstore.dao.ProvinceDao;
import com.jiubo.buildstore.service.ProvinceService;
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
public class ProvinceServiceImpl extends ServiceImpl<ProvinceDao, ProvinceBean> implements ProvinceService {


    @Autowired
    private ProvinceDao provinceDao;
    @Override
    public List<ProvinceBean> getAllProvince() {
        return provinceDao.getAllProvince();
    }
}
