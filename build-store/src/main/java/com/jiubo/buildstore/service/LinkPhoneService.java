package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.LinkPhoneBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface LinkPhoneService extends IService<LinkPhoneBean> {

    public void addLinkPhone(LinkPhoneBean linkPhoneBean);
}
