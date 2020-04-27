package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.RecruitTypeBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
public interface RecruitTypeService extends IService<RecruitTypeBean> {
    public List<RecruitTypeBean> getAllRecruitType();
}
