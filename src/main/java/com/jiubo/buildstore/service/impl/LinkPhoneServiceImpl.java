package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.LinkPhoneBean;

import com.jiubo.buildstore.dao.LinkPhoneDao;
import com.jiubo.buildstore.service.LinkPhoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        String writeDate = linkPhoneBean.getWriteDate();
        Date write = null;
        try{
            write = DateUtils.parseDate(writeDate);
        }catch (Exception e) {
            log.error("日期转换异常" + e);
        }
        linkPhoneBean.setWriteTime(write);
        linkPhoneDao.addLinkPhone(linkPhoneBean);
    }
}
