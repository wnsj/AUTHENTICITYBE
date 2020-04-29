package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.CounselorBean;
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
public interface CounselorDao extends BaseMapper<CounselorBean> {

    public List<CounselorBean> getAllCouselor();

    public List<CounselorBean> getAllCouselorByPage(Page page, @Param("counselorBean") CounselorBean counselorBean);

    public int insertCou(@Param("counselorBean") CounselorBean counselorBean);

    public CounselorBean getCouById(@Param("couId") Integer couId);

    public void patchCouById(@Param("counselorBean") CounselorBean counselorBean);
}
