package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.bean.OfficeBean;
import com.jiubo.buildstore.bean.RoomLabelListBean;
import com.jiubo.buildstore.dao.RoomLabelListDao;
import com.jiubo.buildstore.service.RoomLabelListService;
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
 * @since 2020-10-12
 */
@Service
public class RoomLabelListServiceImpl extends ServiceImpl<RoomLabelListDao, RoomLabelListBean> implements RoomLabelListService {

    @Autowired
    private RoomLabelListDao roomLabelListDao;

    @Override
    public List<RoomLabelListBean> getAllRlb() {
        QueryWrapper<RoomLabelListBean> qwP = new QueryWrapper<RoomLabelListBean>();
        qwP.select("*");
        return roomLabelListDao.selectList(qwP);
    }
}
