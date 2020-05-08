package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.DevelogerBean;

import com.jiubo.buildstore.dao.DevelogerDao;
import com.jiubo.buildstore.service.DevelogerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public void addDeveloger(DevelogerBean develogerBean) throws MessageException {
        if (StringUtils.isBlank(develogerBean.getDevsName())) throw new MessageException("开发商简名不能为空");
        QueryWrapper<DevelogerBean> queryWrapper = new QueryWrapper<DevelogerBean>();
        queryWrapper.select("*");
        queryWrapper.eq("DEVS_NAME", develogerBean.getDevsName());
        List<DevelogerBean> develogerBeans = develogerDao.selectList(queryWrapper);
        if (develogerBeans.size()>0)  throw new MessageException("开发商已经存在");
        develogerDao.insert(develogerBean);
    }

    @Override
    public void updateDeveloger(DevelogerBean develogerBean) throws MessageException{
        if (StringUtils.isBlank(develogerBean.getDevsName())) throw new MessageException("开发商简名不能为空");
        if (develogerBean.getDevId()>0) throw new MessageException("开发商ID不能为空");
        QueryWrapper<DevelogerBean> queryWrapper = new QueryWrapper<DevelogerBean>();
        queryWrapper.select("*");
        queryWrapper.eq("DEVS_NAME", develogerBean.getDevsName());
        List<DevelogerBean> develogerBeans = develogerDao.selectList(queryWrapper);
        if (develogerBeans.size()>0)  throw new MessageException("开发商已经存在");
        develogerDao.updateById(develogerBean);
    }
}
