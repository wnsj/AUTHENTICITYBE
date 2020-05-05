package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.LinkPhoneBean;
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
public interface LinkPhoneDao extends BaseMapper<LinkPhoneBean> {

    public List<LinkPhoneBean> getPhone(Page page, @Param("linkPhoneBean") LinkPhoneBean linkPhoneBean);

    public void addLinkPhone(@Param("linkPhoneBean") LinkPhoneBean linkPhoneBean);

    public void patchLinkById(@Param("linkPhoneBean") LinkPhoneBean linkPhoneBean);

    public void patchFormById(@Param("linkPhoneBean") LinkPhoneBean linkPhoneBean);
}
