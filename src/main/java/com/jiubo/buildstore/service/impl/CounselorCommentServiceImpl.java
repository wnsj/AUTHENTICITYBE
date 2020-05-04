package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.*;

import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.CounselorCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Service
public class CounselorCommentServiceImpl extends ServiceImpl<CounselorCommentDao, CounselorCommentBean> implements CounselorCommentService {

    @Autowired
    private CounselorCommentDao counselorCommentDao;

    @Autowired
    private CouTypeDao couTypeDao;

    @Autowired
    private CouRefDao couRefDao;

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private CounselorDao counselorDao;
    @Override

    /**
     * 前端查询评论
     */
    public CouBean getCounselorByBid(CounselorCommentBean counselorCommentBean) {
        CouBean couBean = new CouBean();
        Page<CounselorCommentBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(counselorCommentBean.getCurrent()) ? 1L : Long.parseLong(counselorCommentBean.getCurrent()));
        page.setSize(StringUtils.isBlank(counselorCommentBean.getPageSize()) ? 10L : Long.parseLong(counselorCommentBean.getPageSize()));

        // 咨询师评论数据
        List<CounselorCommentBean> counselorByBid = counselorCommentDao.getCounselorByBid(page, counselorCommentBean);
        if (null != counselorByBid && counselorByBid.size() > 0) {

            Map<Integer, List<CounselorCommentBean>> map = counselorByBid.stream().collect(Collectors.groupingBy(CounselorCommentBean::getCoucType));

            List<CouTypeBean> allCouTypeList = couTypeDao.getAllCouType();
            Map<Integer, List<CouTypeBean>> couTypeMap = null;
            if (null != allCouTypeList && allCouTypeList.size() > 0) {
                couTypeMap = allCouTypeList.stream().collect(Collectors.groupingBy(CouTypeBean::getId));
            }
            List<CouTypeBean> couTypeBeanList = new ArrayList<>();
            for (Integer couType : map.keySet()) {
                List<CounselorCommentBean> commentBeans = map.get(couType);
                if (null != couTypeMap) {
                   CouTypeBean typeBean = couTypeMap.get(couType).get(0);
                    typeBean.setCouTypeNum(commentBeans.size());
                    couTypeBeanList.add(typeBean);
                }
            }
            couBean.setCouTypeBeanList(couTypeBeanList);
            // 咨询师id集合
            List<Integer> collect = counselorByBid.stream().map(CounselorCommentBean::getCId).collect(Collectors.toList());

            // 通过咨询师id集合查找其标签
            List<CouRefBean> couRefList = couRefDao.getRefByCouIdList(new CouRefBean().setCouIdList(collect));
            Map<Integer, List<CouRefBean>> refMap = null;
            if (null != couRefList && couRefList.size() > 0){
                refMap = couRefList.stream().collect(Collectors.groupingBy(CouRefBean::getCouId));
            }

                //将标签数据放进咨询师评论分页数据中
                for (CounselorCommentBean commentBean : counselorByBid) {
                    if (null != refMap) {
                        List<CouRefBean> counselorLabelBeans = refMap.get(commentBean.getCId());

                        List<String> collect1 = counselorLabelBeans.stream().map(CouRefBean::getCouLabel).collect(Collectors.toList());

                        commentBean.setClContentList(collect1);
                    }
                }
            }
        page.setRecords(counselorByBid);
        couBean.setCouPage(page);
        return couBean;
    }

    @Override
    public void updateNumById(CounselorCommentBean counselorCommentBean) {
        counselorCommentDao.updateNumById(counselorCommentBean);
    }

    @Override
    public Page<CounselorCommentBean> getCounselorByPage(CounselorCommentBean counselorCommentBean) {
        Page<CounselorCommentBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(counselorCommentBean.getCurrent()) ? 1L : Long.parseLong(counselorCommentBean.getCurrent()));
        page.setSize(StringUtils.isBlank(counselorCommentBean.getPageSize()) ? 10L : Long.parseLong(counselorCommentBean.getPageSize()));
        List<CounselorCommentBean> comByPageList = counselorCommentDao.getComByPage(page, counselorCommentBean);
        if (null != comByPageList && comByPageList.size()>0){
            // 获取楼盘名
            List<BuildingBean> allBuildList = buildingDao.getAllBuild();
            Map<Integer, List<BuildingBean>> map = null;
            if (null != allBuildList && allBuildList.size()>0) {
                map = allBuildList.stream().collect(Collectors.groupingBy(BuildingBean::getBuildId));
            }
            // 获取咨询师名
            List<CounselorBean> allCouselorList = counselorDao.getAllCouselor();
            Map<Integer, List<CounselorBean>> listMap = null;
            if (null != allCouselorList && allCouselorList.size()>0){
                listMap = allCouselorList.stream().collect(Collectors.groupingBy(CounselorBean::getCouId));
            }
            // 获取类型
            List<CouTypeBean> typeBeans = couTypeDao.getAllCouType();
            Map<Integer, List<CouTypeBean>> collect = null;
            if (null != typeBeans && typeBeans.size()>0){
                collect = typeBeans.stream().collect(Collectors.groupingBy(CouTypeBean::getId));
            }
            for (CounselorCommentBean commentBean : comByPageList) {
                // 翻译楼盘名
                if (null != map) {
                    commentBean.setHtName(map.get(commentBean.getBuildId()).get(0).getHtName());
                }
                //翻译咨询师名字
                if (null != listMap) {
                    commentBean.setCouName(listMap.get(commentBean.getCId()).get(0).getCouName());
                }
                // 翻译类型
                if (null != collect) {
                    commentBean.setCouTypeName(collect.get(commentBean.getCoucType()).get(0).getCouTypeName());
                }

                if (commentBean.getComDate() != null) {
                    String formatDate = DateUtils.formatDate(commentBean.getComDate(), "yyyy-MM-dd");
                    commentBean.setComTime(formatDate);
                }
            }
        }
        return page.setRecords(comByPageList);
    }


}
