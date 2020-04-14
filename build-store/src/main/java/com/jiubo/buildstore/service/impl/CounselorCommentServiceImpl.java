package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.CounselorCommentBean;

import com.jiubo.buildstore.bean.CounselorLabelBean;
import com.jiubo.buildstore.dao.CounselorCommentDao;
import com.jiubo.buildstore.dao.CounselorLabelDao;
import com.jiubo.buildstore.service.CounselorCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CounselorLabelDao counselorLabelDao;

    @Override
    public Page<CounselorCommentBean> getCounselorByBid(CounselorCommentBean counselorCommentBean) {
        Page<CounselorCommentBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(counselorCommentBean.getPageNum()) ? 1L : Long.parseLong(counselorCommentBean.getPageNum()));
        page.setSize(StringUtils.isBlank(counselorCommentBean.getPageSize()) ? 10L : Long.parseLong(counselorCommentBean.getPageSize()));

        // 咨询师评论数据
        List<CounselorCommentBean> counselorByBid = counselorCommentDao.getCounselorByBid(page, counselorCommentBean);
        if (null != counselorByBid && counselorByBid.size() > 0) {
            // 咨询师id集合
            List<Integer> collect = counselorByBid.stream().map(CounselorCommentBean::getCId).collect(Collectors.toList());

            // 设置标签查询条件
            CounselorLabelBean counselorLabelBean = new CounselorLabelBean();
            counselorLabelBean.setCouIdList(collect);

            // 通过咨询师id集合查找其标签
            List<CounselorLabelBean> labelByCouId = counselorLabelDao.getLabelByCouId(counselorLabelBean);


            if (null != labelByCouId && labelByCouId.size() > 0) {
                // 将标签集合按照咨询师id分组
                Map<Integer, List<CounselorLabelBean>> map = labelByCouId.stream().collect(Collectors.groupingBy(CounselorLabelBean::getCouId));

                //将标签数据放进咨询师评论分页数据中
                for (CounselorCommentBean commentBean : counselorByBid) {
                    List<CounselorLabelBean> counselorLabelBeans = map.get(commentBean.getCId());

                    List<String> collect1 = counselorLabelBeans.stream().map(CounselorLabelBean::getClContent).collect(Collectors.toList());

                    commentBean.setClContentList(collect1);
                }
            }
        }
        return page.setRecords(counselorByBid);
    }

    @Override
    public void updateNumById(CounselorCommentBean counselorCommentBean) {
        counselorCommentDao.updateNumById(counselorCommentBean);
    }
}
