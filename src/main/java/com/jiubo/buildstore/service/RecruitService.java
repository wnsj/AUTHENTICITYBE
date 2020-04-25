package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.RecruitBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-04-25
 */
public interface RecruitService extends IService<RecruitBean> {
    public Page<RecruitBean> getEasyInfo(RecruitBean recruitBean);

    public List<RecruitBean> getDetails(RecruitBean recruitBean);
}
