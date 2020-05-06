package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.RecruitBean;
import com.jiubo.buildstore.bean.RecruitLabelListBean;
import com.jiubo.buildstore.bean.RecruitTypeBean;
import com.jiubo.buildstore.dao.RecruitDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.dao.RecruitTypeDao;
import com.jiubo.buildstore.service.RecruitService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                String s = getDateToString(recruitBean1.getCreateTime());
                recruitBean1.setCreateDate(s);
            }
        }
        return page.setRecords(easyInfoList);
    }

    private String getDateToString(Date date) {
        if (null != date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(date);
        }
        return null;
    }

    @Override
    public List<RecruitBean> getDetails(RecruitBean recruitBean) {

        List<RecruitBean> recruitBeanList = recruitDao.getDetails(recruitBean);
        toLabel(recruitBeanList);
        return recruitBeanList;
    }

    private void toLabel(List<RecruitBean> recruitBeanList) {
        // 翻译职位类型
        List<RecruitTypeBean> recruitTypeList = recruitTypeDao.getAllRecruitType();
        Map<Integer, List<RecruitTypeBean>> map = null;
        if (null != recruitTypeList) {
            map = recruitTypeList.stream().collect(Collectors.groupingBy(RecruitTypeBean::getId));
        }
        if (null != recruitBeanList) {
            for (RecruitBean recruitBean1 : recruitBeanList) {

                if (null != recruitBean1) {
                    if (null != recruitBean1.getFiveRisksFund() && "1".equals(recruitBean1.getFiveRisksFund().toString())) {
                        recruitBean1.setFiveRisksFundLabel("五险一金");
                    }

                    if (null != recruitBean1.getFoodShelter() && "1".equals(recruitBean1.getFoodShelter().toString())) {
                        recruitBean1.setFoodShelterLabel("包吃包住");
                    }

                    if (null != recruitBean1.getFoodShelter() && "1".equals(recruitBean1.getWeekend().toString())) {
                        recruitBean1.setWeekendLabel("双休");
                    }
                    String s = getDateToString(recruitBean1.getCreateTime());
                    recruitBean1.setCreateDate(s);

                    if (null != map && null != recruitBean1.getPositionType()) {
                        recruitBean1.setPositionTypeLabel(map.get(recruitBean1.getPositionType()).get(0).getTypeName());
                    }
                }

            }
        }
    }

    @Override
    public Page<RecruitBean> getRecruitByPage(RecruitBean recruitBean) {
        Page<RecruitBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(recruitBean.getCurrent()) ? 1L : Long.parseLong(recruitBean.getCurrent()));
        page.setSize(StringUtils.isBlank(recruitBean.getPageSize()) ? 10L : Long.parseLong(recruitBean.getPageSize()));
        List<RecruitBean> byPage = recruitDao.getRecruitByPage(page, recruitBean);
        toLabel(byPage);
        return page.setRecords(byPage);
    }

    @Override
    public void addRecruit(RecruitBean recruitBean) {
        recruitBean.setCreateTime(new Date());
        recruitDao.insertRecruit(recruitBean);
    }

    @Override
    public void patchRecruitById(RecruitBean recruitBean) {
        recruitDao.patchRecruitById(recruitBean);
    }

    @Override
    public RecruitLabelListBean getHotRe() {

        RecruitLabelListBean recruitLabelListBean = new RecruitLabelListBean();
        recruitLabelListBean.setHotList(recruitDao.getHotRe(new RecruitBean().setHotJob(1)));
        recruitLabelListBean.setLongList(recruitDao.getHotRe(new RecruitBean().setLongRecruit(1)));

        return recruitLabelListBean;
    }
}
