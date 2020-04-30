package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.RecruitBean;
import com.jiubo.buildstore.bean.RecruitTypeBean;
import com.jiubo.buildstore.dao.RecruitDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.dao.RecruitTypeDao;
import com.jiubo.buildstore.service.RecruitService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
@Service
public class RecruitServiceImpl extends ServiceImpl<RecruitDao, RecruitBean> implements RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Autowired
    private RecruitTypeDao recruitTypeDao;

    @Override
    public Page<RecruitBean> getEasyInfo(RecruitBean recruitBean) {
        Page<RecruitBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(recruitBean.getCurrent()) ? 1L : Long.parseLong(recruitBean.getCurrent()));
        page.setSize(StringUtils.isBlank(recruitBean.getPageSize()) ? 10L : Long.parseLong(recruitBean.getPageSize()));
        // 翻译职位类型
        List<RecruitTypeBean> recruitTypeList = recruitTypeDao.getAllRecruitType();
        Map<Integer, List<RecruitTypeBean>> map = null;
        if (null != recruitTypeList) {
            map = recruitTypeList.stream().collect(Collectors.groupingBy(RecruitTypeBean::getId));
        }
        List<RecruitBean> easyInfoList = recruitDao.getEasyInfo(page, recruitBean);
        if (null != easyInfoList) {
            for (RecruitBean recruitBean1 : easyInfoList) {
                if (null != map) {
                    recruitBean1.setPositionTypeLabel(map.get(recruitBean1.getPositionType()).get(0).getTypeName());
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String s = simpleDateFormat.format(recruitBean1.getCreateTime());
                recruitBean1.setCreateDate(s);
            }
        }
        return page.setRecords(easyInfoList);
    }

    @Override
    public List<RecruitBean> getDetails(RecruitBean recruitBean) {

        List<RecruitBean> recruitBeanList = recruitDao.getDetails(recruitBean);
        if (null != recruitBeanList) {
            for (RecruitBean recruitBean1 : recruitBeanList) {
                if (recruitBean1.getFiveRisksFund() == 1) {
                    recruitBean1.setFiveRisksFundLabel("五险一金");
                }

                if (recruitBean1.getFoodShelter() == 1) {
                    recruitBean1.setFoodShelterLabel("包吃包住");
                }

                if (recruitBean1.getWeekend() == 1) {
                    recruitBean1.setWeekendLabel("双休");
                }
            }
        }
        return recruitBeanList;
    }
}
