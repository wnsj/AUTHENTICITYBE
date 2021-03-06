package com.jiubo.buildstore.service;

import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.RecruitLabelBean;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiubo.buildstore.bean.RecruitLabelListBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
public interface RecruitLabelService extends IService<RecruitLabelBean> {

    public List<RecruitLabelBean> getLabelByType(RecruitLabelBean recruitLabelBean);

    public void addRecruitLabel(RecruitLabelBean recruitLabelBean)throws MessageException;
}
