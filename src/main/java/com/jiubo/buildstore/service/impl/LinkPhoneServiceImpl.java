package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.LinkPhoneBean;

import com.jiubo.buildstore.dao.LinkPhoneDao;
import com.jiubo.buildstore.service.LinkPhoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class LinkPhoneServiceImpl extends ServiceImpl<LinkPhoneDao, LinkPhoneBean> implements LinkPhoneService {


    @Autowired
    private LinkPhoneDao linkPhoneDao;

    @Override
    public Page<LinkPhoneBean> getPhone(LinkPhoneBean linkPhoneBean) {
        Page<LinkPhoneBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(linkPhoneBean.getCurrent()) ? 1L : Long.parseLong(linkPhoneBean.getCurrent()));
        page.setSize(StringUtils.isBlank(linkPhoneBean.getPageSize()) ? 10L : Long.parseLong(linkPhoneBean.getPageSize()));
        List<LinkPhoneBean> linkPhoneBeanList = linkPhoneDao.getPhone(page, linkPhoneBean);
        if (null != linkPhoneBeanList && linkPhoneBeanList.size()>0) {
            for (LinkPhoneBean linkPhoneBean1 : linkPhoneBeanList) {
                if (null != linkPhoneBean1.getWriteTime()) {
                    String formatDate = DateUtils.formatDate(linkPhoneBean1.getWriteTime(), "yyyy-MM-dd");
                    linkPhoneBean1.setWriteDate(formatDate);
                }

                if (linkPhoneBean1.getCreateDate() != null) {
                    String formatDate = DateUtils.formatDate(linkPhoneBean1.getCreateDate(), "yyyy-MM-dd");
                    linkPhoneBean1.setCreateTime(formatDate);
                }

                if (linkPhoneBean1.getRemarks() == 2) {
                    linkPhoneBean1.setRemarksLabel("未回拨");
                } else {
                    linkPhoneBean1.setRemarksLabel("已回拨");
                }
            }
        }

        return page.setRecords(linkPhoneBeanList);
    }

    @Override
    public void addLinkPhone(LinkPhoneBean linkPhoneBean) {
        if (null != linkPhoneBean) {
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

    @Override
    public void patchLinkById(LinkPhoneBean linkPhoneBean) {
        linkPhoneDao.patchLinkById(linkPhoneBean);
    }

    @Override
    public void patchFormById(LinkPhoneBean linkPhoneBean) {
        linkPhoneDao.patchFormById(linkPhoneBean);
    }
}
