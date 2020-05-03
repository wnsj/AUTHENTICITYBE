package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.RecruitBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
public interface RecruitDao extends BaseMapper<RecruitBean> {
    public List<RecruitBean> getEasyInfo(Page page, @Param("recruitBean") RecruitBean recruitBean);

    public List<RecruitBean> getDetails(@Param("recruitBean") RecruitBean recruitBean);


    public List<RecruitBean> getRecruitByPage(Page page,@Param("recruitBean") RecruitBean recruitBean);

    public void insertRecruit(@Param("recruitBean") RecruitBean recruitBean);

    public void patchRecruitById(@Param("recruitBean") RecruitBean recruitBean);
}
