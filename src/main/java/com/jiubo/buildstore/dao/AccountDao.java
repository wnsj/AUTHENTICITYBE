package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiubo.buildstore.bean.AccountBean;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author mwl
 * @since 2020-02-10
 */
public interface AccountDao extends BaseMapper<AccountBean> {
    public List<AccountBean> queryAccountList(AccountBean accountBean);

//    int insertAccount(AccountBean accountBean);
}
