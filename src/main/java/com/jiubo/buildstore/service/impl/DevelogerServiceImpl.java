package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.DevelogerBean;

import com.jiubo.buildstore.dao.DevelogerDao;
import com.jiubo.buildstore.service.DevelogerService;
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
public class DevelogerServiceImpl extends ServiceImpl<DevelogerDao, DevelogerBean> implements DevelogerService {

    @Autowired
    private DevelogerDao develogerDao;
    @Override
    public List<DevelogerBean> getAllDev() {
        return develogerDao.getAllDev();
    }
}
