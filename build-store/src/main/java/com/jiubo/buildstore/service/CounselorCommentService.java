package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.CounselorCommentBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface CounselorCommentService extends IService<CounselorCommentBean> {

    public Page<CounselorCommentBean> getCounselorByBid(CounselorCommentBean counselorCommentBean);

    public void updateNumById(CounselorCommentBean counselorCommentBean);
}
