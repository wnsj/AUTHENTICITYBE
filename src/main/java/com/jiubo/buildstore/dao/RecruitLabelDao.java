package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.RecruitLabelBean;
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
public interface RecruitLabelDao extends BaseMapper<RecruitLabelBean> {

    public List<RecruitLabelBean> getLabelByType(@Param("recruitLabelBean") RecruitLabelBean recruitLabelBean);
}
