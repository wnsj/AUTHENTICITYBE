package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.RecruitTypeBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-04-25
 */
public interface RecruitTypeDao extends BaseMapper<RecruitTypeBean> {

    public List<RecruitTypeBean> getAllRecruitType();
}
