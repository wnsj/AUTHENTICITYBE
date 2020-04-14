package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.LinkPhoneBean;

import com.jiubo.buildstore.dao.LinkPhoneDao;
import com.jiubo.buildstore.service.LinkPhoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Service
public class LinkPhoneServiceImpl extends ServiceImpl<LinkPhoneDao, LinkPhoneBean> implements LinkPhoneService {


    @Autowired
    private LinkPhoneDao linkPhoneDao;
    @Override
    public void addLinkPhone(LinkPhoneBean linkPhoneBean) {
        linkPhoneDao.addLinkPhone(linkPhoneBean);
    }
}
