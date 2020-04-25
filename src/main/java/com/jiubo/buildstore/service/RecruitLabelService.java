package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.RecruitLabelBean;
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
public interface RecruitLabelService extends IService<RecruitLabelBean> {

    public List<RecruitLabelBean> getLabelByType(RecruitLabelBean recruitLabelBean);
}
