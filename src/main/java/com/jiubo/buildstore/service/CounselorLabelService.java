package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.CounselorLabelBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface CounselorLabelService extends IService<CounselorLabelBean> {
    public List<CounselorLabelBean> getAllCouLabel();
}
