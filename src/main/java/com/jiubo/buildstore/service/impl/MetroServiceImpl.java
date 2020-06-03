package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.MetroBean;
import com.jiubo.buildstore.dao.MetroDao;
import com.jiubo.buildstore.service.MetroService;
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
 * @since 2020-06-03
 */
@Service
public class MetroServiceImpl extends ServiceImpl<MetroDao, MetroBean> implements MetroService {

    @Autowired
    private MetroDao metroDao;
    @Override
    public List<MetroBean> getAllMetro() {
        return metroDao.getAllMetro();
    }
}
