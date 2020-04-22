package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.HorseTypeBean;

import com.jiubo.buildstore.dao.HorseTypeDao;
import com.jiubo.buildstore.service.HorseTypeService;
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
public class HorseTypeServiceImpl extends ServiceImpl<HorseTypeDao, HorseTypeBean> implements HorseTypeService {

    @Autowired
    private HorseTypeDao horseTypeDao;
    @Override
    public List<HorseTypeBean> getAllHorseType() {
        return horseTypeDao.getAllHorseType();
    }
}
