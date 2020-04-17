package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.CounselorCommentBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface CounselorCommentDao extends BaseMapper<CounselorCommentBean> {

    public List<CounselorCommentBean> getCounselorByBid(Page page,@Param("counselorCommentBean") CounselorCommentBean counselorCommentBean);

    public void updateNumById(@Param("counselorCommentBean") CounselorCommentBean counselorCommentBean);

    public List<CounselorCommentBean> getCidByBidList(@Param("counselorCommentBean") CounselorCommentBean counselorCommentBean);
}
