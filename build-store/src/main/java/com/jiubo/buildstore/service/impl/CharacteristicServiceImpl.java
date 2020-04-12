package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.CharacteristicBean;

import com.jiubo.buildstore.dao.CharacteristicDao;
import com.jiubo.buildstore.service.CharacteristicService;
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
public class CharacteristicServiceImpl extends ServiceImpl<CharacteristicDao, CharacteristicBean> implements CharacteristicService {

    @Autowired
    private CharacteristicDao characteristicDao;
    @Override
    public List<CharacteristicBean> getAllChara() {
        return characteristicDao.getAllChara();
    }
}
