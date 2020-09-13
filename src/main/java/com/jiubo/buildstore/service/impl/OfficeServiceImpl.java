package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.OfficeBean;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.OfficeDao;
import com.jiubo.buildstore.service.OfficeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.CollectionsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-13
 */
@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeDao, OfficeBean> implements OfficeService {

    @Autowired
    private OfficeDao officeDao;
    @Autowired
    private BuildingImgDao buildingImgDao;
    @Override
    public OfficeBean getOfficeByPk(Integer id) {
        OfficeBean bean = officeDao.selectById(id);
        if (null != bean) {
            QueryWrapper<BuildingImgBean> qwP = new QueryWrapper<BuildingImgBean>();
            qwP.select("*");
            qwP.eq("IT_ID", 2);
            qwP.eq("TYPE", 4);
            qwP.eq("INFO_ID", bean.getId());
            List<BuildingImgBean> pictureList = buildingImgDao.selectList(qwP);
            if (!CollectionsUtils.isEmpty(pictureList)) {
                List<String> list = pictureList.stream().map(BuildingImgBean::getImgPath).collect(Collectors.toList());
                bean.setPicList(list);
            }
        }
        return bean;
    }
}
