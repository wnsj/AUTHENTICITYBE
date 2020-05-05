package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.LinkPhoneBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface LinkPhoneService extends IService<LinkPhoneBean> {

    public Page<LinkPhoneBean> getPhone(LinkPhoneBean linkPhoneBean);

    public void addLinkPhone(LinkPhoneBean linkPhoneBean);

    public void patchLinkById(LinkPhoneBean linkPhoneBean);
}
