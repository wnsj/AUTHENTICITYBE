package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.LinkPhoneBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface LinkPhoneDao extends BaseMapper<LinkPhoneBean> {

    public void addLinkPhone(@Param("linkPhoneBean") LinkPhoneBean linkPhoneBean);
}
